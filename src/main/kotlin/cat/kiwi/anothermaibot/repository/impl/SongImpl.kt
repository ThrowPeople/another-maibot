package cat.kiwi.anothermaibot.repository.impl

import cat.kiwi.anothermaibot.config.UrlConfig
import cat.kiwi.anothermaibot.exception.ForbiddenException
import cat.kiwi.anothermaibot.model.FlattenSongItem
import cat.kiwi.anothermaibot.model.SongItem
import cat.kiwi.anothermaibot.repository.PlayerAchievement
import cat.kiwi.anothermaibot.repository.Song
import com.google.gson.Gson
import org.apache.http.client.methods.HttpGet
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class SongImpl : Song {
    companion object {
        var songListCache = arrayOf<SongItem>()
    }

    private val log = LoggerFactory.getLogger(SongImpl::class.java)


    @Autowired
    lateinit var playerAchievement: PlayerAchievement

    @Autowired
    lateinit var urlConfig: UrlConfig
    private val songList: Array<SongItem>
        get() {
            if (songListCache.isNotEmpty()) {
                log.info("[songList] Music data hit local cache!")
                return songListCache
            }
            log.info("[songList] Getting music data from diving-fish api!")
            val client = org.apache.http.impl.client.HttpClients.createDefault()
            val get = HttpGet(urlConfig.dfApiMusicData)

            val response = client.execute(get)

            response.entity.content.bufferedReader().use {
                val json = it.readText()
                val gson = Gson()
                songListCache = gson.fromJson(json, Array<SongItem>::class.java)
                return songListCache
            }
        }

    override fun findSongByVersion(version: String): List<SongItem> {
        return songList.filter { it.basic_info.from == version }
    }

    override fun getPlateFlatten(version: String, id: Long): List<FlattenSongItem> {
        val versionSongs = findSongByVersion(version)
        val versionSongFlatten = arrayListOf<FlattenSongItem>()
        val ach = playerAchievement.getAchievementByVersion(id, version).verlist
        if (ach.isEmpty()) {
            throw ForbiddenException()
        }

        versionSongs.forEach { song ->
            song.level.forEachIndexed { index, level ->
                versionSongFlatten.add(FlattenSongItem(song.id, index, song.title, level, 0.0, "", "", urlConfig))
            }
        }

        versionSongFlatten.map {
            val songList = ach.find { ach ->
                ach.id.toString() == it.id && ach.level_index == it.levelIndex
            }
            if (songList != null) {
                it.score = songList.achievements
                it.fc = songList.fc
                it.fs = songList.fs
                songList
            } else {
                it
            }
        }
        return versionSongFlatten
    }
}
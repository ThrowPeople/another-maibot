package cat.kiwi.anothermaibot.repository

import cat.kiwi.anothermaibot.model.FlattenSongItem
import cat.kiwi.anothermaibot.model.SongItem

interface Song {
    fun findSongByVersion(version: String): List<SongItem>

    fun getPlateFlatten(version: String, id: Long): List<FlattenSongItem>

}
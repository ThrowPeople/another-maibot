package cat.kiwi.anothermaibot.service.impl

import cat.kiwi.anothermaibot.const.Plate.Companion.suffixList
import cat.kiwi.anothermaibot.const.Plate.Companion.versionID
import cat.kiwi.anothermaibot.model.FlattenSongItem
import cat.kiwi.anothermaibot.repository.Song
import cat.kiwi.anothermaibot.service.RenderService
import cat.kiwi.anothermaibot.utils.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RenderServiceImpl : RenderService {
    @Autowired
    private lateinit var song: Song
    override fun renderPlateList(id: Long, prefix: Int, suffix: Int, done: Boolean): List<FlattenSongItem> {
        val ach = song.getPlateFlatten(versionID[prefix], id.toLong())
        var result: List<FlattenSongItem> = listOf()

        when (suffixList[suffix]) {
            '极' -> result = ach.getJi(done)
            '将' -> result = ach.getJiang(done)
            '神' -> result = ach.getShen(done)
            '舞' -> result = ach.getWuWu(done)
            '者' -> result = ach.getBaZhe(done)
        }
        return result.sortedBy { it.levelIndex }
    }
}
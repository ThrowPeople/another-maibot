package cat.kiwi.anothermaibot.service

import cat.kiwi.anothermaibot.model.FlattenSongItem

interface RenderService {
    fun renderPlateList(id:Long, prefix:Int,suffix:Int,done:Boolean): List<FlattenSongItem>
}
package cat.kiwi.anothermaibot.model

import cat.kiwi.anothermaibot.config.UrlConfig

data class FlattenSongItem(
    var id: String,
    val levelIndex: Int,
    val title: String,
    val level: String,
    var score: Double,
    var fc: String,
    var fs: String,
    var urlConfig: UrlConfig
) {
    val containerStyle: String
        get() {
            return when (levelIndex) {
                0 -> "background-color: #22bb5b"
                1 -> "background-color: #ffba01"
                2 -> "background-color: #ff5a66"
                3 -> "background-color: #9f51dc"
                else -> "background-color: #e6e6e6"
            }
        }
    val lightContainerStyle: String
        get() {
            return when (levelIndex) {
                0 -> "background-color: #81d955"
                1 -> "background-color: #f8df3a"
                2 -> "background-color: #ff8c97"
                3 -> "background-color: #c98cf6"
                else -> "background-color: #dbaaff"
            }
        }
    val fcStyle: String
        get() {
            return when (fc) {
                "fc" -> "background-image: url(\"${urlConfig.staticBase}/mai/pic/UI_MSS_MBase_Icon_FC.png\")"
                "fcp" -> "background-image: url(\"${urlConfig.staticBase}/mai/pic/UI_MSS_MBase_Icon_FCp.png\")"
                "ap" -> "background-image: url(\"${urlConfig.staticBase}/mai/pic/UI_MSS_MBase_Icon_AP.png\")"
                "app" -> "background-image: url(\"${urlConfig.staticBase}/mai/pic/UI_MSS_MBase_Icon_APp.png\")"
                else -> ""
            }
        }
    val fsStyle: String
        get() {
            return when (fs) {
                "fs" -> "background-image: url(\"${urlConfig.staticBase}/mai/pic/UI_MSS_MBase_Icon_FS.png\")"
                "fsp" -> "background-image: url(\"${urlConfig.staticBase}/mai/pic/UI_MSS_MBase_Icon_FSp.png\")"
                "fsd" -> "background-image: url(\"${urlConfig.staticBase}/mai/pic/UI_MSS_MBase_Icon_FSD.png\")"
                "fsdp" -> "background-image: url(\"${urlConfig.staticBase}/mai/pic/UI_MSS_MBase_Icon_FSDp.png\")"
                else -> ""
            }
        }
}


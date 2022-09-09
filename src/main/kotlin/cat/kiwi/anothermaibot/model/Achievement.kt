package cat.kiwi.anothermaibot.model


data class Achievement(
    val verlist: List<VerList>
)

data class VerList(
    val achievements: Double,
    val fc: String,
    val fs: String,
    val id: Int,
    val level: String,
    val level_index: Int,
    val title: String,
    val type: String
)
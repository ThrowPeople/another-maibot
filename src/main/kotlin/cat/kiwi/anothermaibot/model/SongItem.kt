package cat.kiwi.anothermaibot.model

data class SongItem(
    val basic_info: BasicInfo,
    val charts: List<Chart>,
    val cids: List<Int>,
    val ds: List<Double>,
    val id: String,
    val level: List<String>,
    val title: String,
    val type: String
)

data class BasicInfo(
    val artist: String,
    val bpm: Int,
    val from: String,
    val genre: String,
    val is_new: Boolean,
    val release_date: String,
    val title: String
)

data class Chart(
    val charter: String,
    val notes: List<Int>
)
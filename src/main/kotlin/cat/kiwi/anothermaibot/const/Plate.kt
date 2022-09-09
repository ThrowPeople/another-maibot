package cat.kiwi.anothermaibot.const

class Plate {
    companion object {
        const val PLATE_RULES =
            "极：FC\n" + "将：SSS\n" + "神：AP\n" + "舞舞：FDX\n" + "覇者(暂不支持查询)：通关所有\n" + "\n" + "maimai 真\n" + "GreeN 超 PLUS 檄\n" + "ORANGE 橙 PLUS 晓\n" + "PiNK 桃 PLUS 樱\n" + "MURASAKi 紫 PLUS 堇\n" + "MiLK 白 PLUD 雪\n" + "FiNALE 辉\n" + "maimai-FiNALE 舞(暫不支持)\n" + "DX 熊 PLUS 华\n" + "Splash 爽 PLUS 煌\n" + "UNiVERSE 宙\n\n" + "范例(未完成): 查询 牌子 真极\n" + "范例(已完成): 查询 牌子 真极 已完成"

        val versionID: List<String> = arrayListOf(
            "maimai",
            "maimai GreeN",
            "maimai GreeN PLUS",
            "maimai ORANGE",
            "maimai ORANGE PLUS",
            "maimai PiNK",
            "maimai PiNK PLUS",
            "maimai MURASAKi",
            "maimai MURASAKi PLUS",
            "maimai MiLK",
            "maimai MiLK PLUS",
            "maimai FiNALE",
            "maimai でらっくす",
            "maimai でらっくす PLUS",
            "maimai でらっくす Splash",
            "maimai でらっくす Splash PLUS",
            "maimai でらっくす UNiVERSE"
        )
        val suffixList = arrayListOf('极', '将', '神', '舞', '者')
        val Char.maiMaiVersion: String
            get() {
                return when (this) {
                    '真' -> "maimai"
                    '超' -> "maimai GreeN"
                    '檄' -> "maimai GreeN PLUS"
                    '橙' -> "maimai ORANGE"
                    '晓' -> "maimai ORANGE PLUS"
                    '桃' -> "maimai PiNK"
                    '樱' -> "maimai PiNK PLUS"
                    '紫' -> "maimai MURASAKi"
                    '堇' -> "maimai MURASAKi PLUS"
                    '白' -> "maimai MiLK"
                    '雪' -> "maimai MiLK PLUS"
                    '辉' -> "maimai FiNALE"
                    '熊' -> "maimai でらっくす"
                    '华' -> "maimai でらっくす PLUS"
                    '爽' -> "maimai でらっくす Splash"
                    '煌' -> "maimai でらっくす Splash PLUS"
                    '宙' -> "maimai UNiVERSE"
                    '霸' -> "ALL"
                    else -> ""
                }
            }


    }

}

package cat.kiwi.anothermaibot.utils

val String.confirm: Boolean
    get() = this == "已完成" || this == "完成" || this == "已达成" || this == "达成" || this == "已确认" || this == "确认"

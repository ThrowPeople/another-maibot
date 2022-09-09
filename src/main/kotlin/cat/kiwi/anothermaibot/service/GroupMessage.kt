package cat.kiwi.anothermaibot.service

interface GroupMessage {
    fun send(message: String, groupID: Long)
}
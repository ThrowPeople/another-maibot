package cat.kiwi.anothermaibot.service

interface MessageService {
    fun handleGroupMessage(userID: Long, groupID: Long, message: String?)
}




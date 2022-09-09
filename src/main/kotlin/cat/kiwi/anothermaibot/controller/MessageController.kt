package cat.kiwi.anothermaibot.controller

import cat.kiwi.anothermaibot.config.UrlConfig
import cat.kiwi.anothermaibot.service.MessageService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController {
    private val log = LoggerFactory.getLogger(MessageService::class.java)
    @Autowired
    private lateinit var messageService: MessageService

    @Autowired
    private lateinit var urlConfig: UrlConfig

    @RequestMapping(method = [RequestMethod.POST], value = ["/"])
    fun send(@RequestBody request: Map<String, Any>) {
        try {
            val postType = request["post_type"]
            if (postType == "meta_event") return
            val msgType = request["message_type"]
            if (msgType != "group") return

            val userID = request["user_id"].toString().toLong()
            val groupID = request["group_id"].toString().toLong()

            val message = request["message"]

            log.info("[$groupID][$userID] $message")
            messageService.handleGroupMessage(userID, groupID, message as String?)

        } catch (e: Exception) {
            e.printStackTrace()

            log.warn("Invalid request")
            log.warn(request.toString())
        }

    }
}
package cat.kiwi.anothermaibot.service.impl

import cat.kiwi.anothermaibot.config.UrlConfig
import cat.kiwi.anothermaibot.service.GroupMessage
import com.google.gson.Gson
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GroupMessageImpl : GroupMessage {

    @Autowired
    lateinit var urlConfig: UrlConfig

    override fun send(message: String, groupID: Long) {
        sendBySlice(message, groupID)
    }

    private fun sendBySlice(message: String, groupID: Long) {
        for (i in 0..message.length / 350) {
            if ((i + 1) * 350 > message.length) {
                internalSend(message.substring(i * 350, message.length), groupID)
                return
            }
            val chunkedMsg = message.substring(i * 350, (i + 1) * 350)
            internalSend(chunkedMsg, groupID)
        }
    }

    private fun internalSend(message: String, groupID: Long) {
        val client = org.apache.http.impl.client.HttpClients.createDefault()
        val post = HttpPost(urlConfig.cqPostUrl)

        post.setHeader("Content-type", "application/json")
        val request = hashMapOf<String, Any>()
        request["group_id"] = groupID
        request["message"] = message

        val entity = StringEntity(Gson().toJson(request), Charsets.UTF_8)
        post.entity = entity

        client.execute(post)
    }
}
package cat.kiwi.anothermaibot.service.impl

import cat.kiwi.anothermaibot.config.UrlConfig
import cat.kiwi.anothermaibot.const.Plate.Companion.PLATE_RULES
import cat.kiwi.anothermaibot.const.Plate.Companion.maiMaiVersion
import cat.kiwi.anothermaibot.const.Plate.Companion.suffixList
import cat.kiwi.anothermaibot.const.Plate.Companion.versionID
import cat.kiwi.anothermaibot.exception.ForbiddenException
import cat.kiwi.anothermaibot.exception.NotFoundException
import cat.kiwi.anothermaibot.service.GroupMessage
import cat.kiwi.anothermaibot.service.MessageService
import cat.kiwi.anothermaibot.utils.confirm
import com.google.gson.Gson
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.util.EntityUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import kotlin.jvm.Throws

@Service
class MessageServiceImpl : MessageService {
    private val log = LoggerFactory.getLogger(MessageServiceImpl::class.java)

    @Autowired
    lateinit var groupMessage: GroupMessage

    @Autowired
    lateinit var urlConfig: UrlConfig

    override fun handleGroupMessage(userID: Long, groupID: Long, message: String?) {
        val patten = message!!.split(" ")
        when (patten[0]) {
            "查询" -> {
                try {
                    when (patten[1]) {
                        "牌子" -> {
                            plateHandler(userID, groupID, patten)
                        }

                        else -> {
                            groupMessage.send(PLATE_RULES, groupID)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    groupMessage.send("查询失败", groupID)
                    groupMessage.send(PLATE_RULES, groupID)
                }
            }

            else -> {}
        }
    }

    private fun plateHandler(userID: Long, groupID: Long, message: List<String>) {
        val prefix = message[2][0]
        val suffix = message[2].last()
        val done = message.last()

        sendPic(prefix, suffix, done, userID, groupID)
    }

    private fun sendPic(prefix: Char, suffix: Char, done: String, userID: Long, groupID: Long) {
        val versionPrefix = versionID.indexOf(prefix.maiMaiVersion)
        val versionSuffix = suffixList.indexOf(suffix)
        try {
            val url = getPicLink(userID, versionPrefix, versionSuffix, done.confirm)
            groupMessage.send("[CQ:image,file=$url,id=40000]", groupID)
        } catch (e: ForbiddenException) {
            groupMessage.send("查询失败，您未授权查询分数或未上传分数", groupID)
        } catch (e: NotFoundException) {
            groupMessage.send("查询失败，未找到资源", groupID)
        } catch (e: Exception) {
            groupMessage.send("查询失败，原因可能是网络超时", groupID)
        }
    }


    @Throws(ResponseStatusException::class)
    private fun getPicLink(id: Long, versionPrefix: Int, versionSuffix: Int, done: Boolean): String {
        val requestAddress =
            "${urlConfig.springRenderPlateListUrl}?id=$id&prefix=$versionPrefix&suffix=$versionSuffix&done=$done"
        val client = org.apache.http.impl.client.HttpClients.createDefault()
        val post = HttpPost(urlConfig.nodeScreenShotUrl)
        val request = hashMapOf<String, Any>()
        request["url"] = requestAddress
        val entity = StringEntity(Gson().toJson(request), Charsets.UTF_8)
        post.entity = entity
        post.setHeader("Content-type", "application/json")

        log.info("[getPicLink] Requesting address: $requestAddress")
        val response = client.execute(post)
        if (response.statusLine.statusCode == 200) {
            return EntityUtils.toString(response.entity)
        }
        if (response.statusLine.statusCode == 403) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN)
        }
        if (response.statusLine.statusCode == 404) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        if(response.statusLine.statusCode == 500) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
        }

        return "error"
    }

}

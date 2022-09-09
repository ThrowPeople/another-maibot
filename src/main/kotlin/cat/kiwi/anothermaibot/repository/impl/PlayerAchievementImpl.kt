package cat.kiwi.anothermaibot.repository.impl

import cat.kiwi.anothermaibot.config.UrlConfig
import cat.kiwi.anothermaibot.model.Achievement
import cat.kiwi.anothermaibot.repository.PlayerAchievement
import com.google.gson.Gson
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class PlayerAchievementImpl: PlayerAchievement {
    private val client = org.apache.http.impl.client.HttpClients.createDefault()
    @Autowired
    lateinit var urlConfig: UrlConfig

    override fun getAchievementByVersion(id: Long, version: String): Achievement {
        val post = HttpPost(urlConfig.dfApiQueryPlate)
        val request = hashMapOf<String, Any>()
        request["qq"] = id
        request["version"] = arrayListOf(version)
        val gson = Gson().toJson(request)

        post.entity = StringEntity(gson.toString(), Charsets.UTF_8)
        post.setHeader("Content-Type", "application/json")
        val response = client.execute(post)
        val string = response.entity.content.bufferedReader()
        return Gson().fromJson(string, Achievement::class.java)
    }
}
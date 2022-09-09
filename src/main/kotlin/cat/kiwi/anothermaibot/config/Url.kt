package cat.kiwi.anothermaibot.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "bot.url")
data class UrlConfig(
    var nodeScreenShotUrl: String = "",
    var springRenderPlateListUrl: String = "",
    var cqPostUrl: String = "",
    var dfApiQueryPlate: String = "",
    var dfApiMusicData: String = "",
    var staticBase: String = ""
    )
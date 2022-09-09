package cat.kiwi.anothermaibot

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync


@AutoConfiguration
@SpringBootApplication
@EnableAsync
class AnotherMaibot {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<AnotherMaibot>(*args)
        }
    }
}

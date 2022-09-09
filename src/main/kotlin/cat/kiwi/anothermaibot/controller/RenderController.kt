package cat.kiwi.anothermaibot.controller

import cat.kiwi.anothermaibot.exception.ForbiddenException
import cat.kiwi.anothermaibot.service.RenderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpServletRequest

@Controller
class RenderController {
    @Autowired
    private lateinit var renderService: RenderService

    @GetMapping("/template/render/plate/list")
    fun renderPlateList(
        @RequestParam id: Long, prefix: Int, suffix: Int, done: Boolean, request: HttpServletRequest
    ): String {
        try {
            val result = renderService.renderPlateList(id, prefix, suffix, done)
            request.setAttribute("data", result)
        } catch (e: ResponseStatusException) {
            throw ResponseStatusException(e.status)
        }
        return "plate"
    }
}
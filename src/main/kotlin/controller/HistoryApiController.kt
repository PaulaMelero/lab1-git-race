package es.unizar.webeng.hello.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import es.unizar.webeng.hello.HistoryService
import es.unizar.webeng.hello.HistoryLog

/**
 * REST API controller providing access to the greeting history logs.
 */
@RestController
@RequestMapping("/api/history")
class HistoryApiController(private val historyService: HistoryService) {

    /**
     * Returns the list of recent user interaction logs.
     *
     * @return A list of [HistoryLog] instances containing logged greeting records.
     */
    @GetMapping
    fun getHistory(): List<HistoryLog> {
        return historyService.getRecentLogs()
    }
}
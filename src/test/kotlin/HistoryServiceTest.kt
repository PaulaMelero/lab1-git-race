package es.unizar.webeng.hello

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HistoryServiceTest {

    private val service = HistoryService()

    @Test
    fun `should store and retrieve logs correctly`() {
        service.addLog("Paula", "Hello, Paula!")
        val logs = service.getRecentLogs()

        assertEquals(1, logs.size)
        assertEquals("Paula", logs[0].name)
        assertEquals("Hello, Paula!", logs[0].message)
    }
}
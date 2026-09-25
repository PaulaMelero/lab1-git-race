package es.unizar.webeng.hello.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import es.unizar.webeng.hello.HistoryService

class HistoryApiControllerTest {

    @Test
    fun `should return recent logs from history service`() {
        val service = HistoryService()
        service.addLog("Paula", "¡Hola!")
        
        val controller = HistoryApiController(service)
        val response = controller.getHistory()

        assertThat(response).hasSize(1)
        assertThat(response[0].name).isEqualTo("Paula")
        assertThat(response[0].message).isEqualTo("¡Hola!")
    }
}
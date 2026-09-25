package es.unizar.webeng.hello.controller

import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import es.unizar.webeng.hello.HistoryService
import es.unizar.webeng.hello.HistoryLog

@WebMvcTest(HelloController::class)
class HelloControllerMVCTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var historyService: HistoryService

    @Test
    fun `should return home page with default message`() {
        `when`(historyService.getRecentLogs()).thenReturn(emptyList())

        mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(view().name("welcome"))
            .andExpect(model().attributeExists("message"))
            .andExpect(model().attributeExists("logs"))
    }

    @Test
    fun `should return home page with personalized message`() {
        `when`(historyService.getRecentLogs()).thenReturn(listOf(HistoryLog("Paula", "Hello, Paula!")))

        mockMvc.perform(get("/").param("name", "Paula"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(view().name("welcome"))
            .andExpect(model().attribute("message", equalTo("Hello, Paula!")))
            .andExpect(model().attribute("name", equalTo("Paula")))
    }
}
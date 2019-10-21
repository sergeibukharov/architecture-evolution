package com.thoughtworks.people.controller

import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class PeopleControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun testMy() {
        val expectedHeader = "<h1><img src=\"https://avatars.dicebear.com/v2/male/my-somffething.svg\" height=\"48\" width=\"48\">Sergey Bukharov</h1>"
        val expectedBirthdate = "<p>Birth date: 1 DECEMBER 1987</p>"
        val expectedQuote = "<p>Favorite quote: make the easy things easy, and the hard things possible</p>"

        mvc.get("/me") {
            contentType = MediaType.TEXT_HTML
        }.andExpect {
            status { is2xxSuccessful }
        }.andReturn()
                .response
                .contentAsString
                .apply { contains(expectedHeader) }
                .apply { contains(expectedBirthdate) }
                .apply { contains(expectedQuote) }
    }
}

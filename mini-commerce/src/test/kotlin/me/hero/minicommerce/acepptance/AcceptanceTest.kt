package me.hero.minicommerce.acepptance

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
open class AcceptanceTest {
    @LocalServerPort
    var port = 0
    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }
}
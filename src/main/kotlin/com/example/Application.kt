package com.example

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.setBody
import io.ktor.serialization.jackson.jackson
import kotlinx.coroutines.runBlocking

data class TestData(
    val field: String
)

fun main() {
    runBlocking {
        val client = HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                jackson()
            }

            defaultRequest {
                url("https://google.com")

                contentType(ContentType.Application.Json)
            }
        }

        client.post("/someapi") {
            setBody(TestData("Hello"))
        }
    }
}

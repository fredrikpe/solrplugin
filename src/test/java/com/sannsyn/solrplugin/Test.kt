package com.sannsyn.solrplugin

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class Test {
    @Test
    fun test_httpRequest() {
        runBlocking {
            HttpClient(Apache).use {
                it.get<String>("https://google.com/")
            }
        }
    }
}

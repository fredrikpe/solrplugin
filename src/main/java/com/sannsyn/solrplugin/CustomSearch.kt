package com.sannsyn.solrplugin

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import kotlinx.coroutines.runBlocking
import org.apache.solr.handler.component.ResponseBuilder
import org.apache.solr.handler.component.SearchComponent


class CustomSearch : SearchComponent() {
    override fun process(rb: ResponseBuilder) {

        val numDocsFound = rb.results.docList.size()
        if (numDocsFound == 0) {
            reportZeroHit(rb.queryString)
        }

        rb.rsp.add("queryString", rb.queryString)
        rb.rsp.add("req.paramString", rb.req.paramString)
        rb.rsp.add("query tostring", "${rb.query}")
        rb.rsp.add("num docs found", "${rb.results.docList.size()}")
    }

    private fun reportZeroHit(queryString: String) {
        runBlocking {
            HttpClient(Apache).use {
                it.post<String> {
                    url("http://localhost:8989/api/zero-hit")
                    body = "{ \"queryString\":$queryString, \"count\":1, \"coreName\":\"films\" }"
                }
            }
        }
    }

    override fun prepare(rb: ResponseBuilder) {
    }

    override fun getDescription(): String {
        return "CustomSearch description."
    }
}

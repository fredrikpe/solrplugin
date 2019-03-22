package com.sannsyn.solrplugin

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.solr.common.util.NamedList
import org.apache.solr.handler.component.ResponseBuilder
import org.apache.solr.handler.component.SearchComponent


class CustomSearch : SearchComponent() {
    override fun process(rb: ResponseBuilder) {

        if (rb.numberDocumentsFound == 0L) {
            reportZeroHit(rb.queryString)
        }

        //rb.rsp.add("coreName", coreName)
        rb.rsp.add("rb.debugInfo", rb.debugInfo)
        rb.rsp.add("rb.req", rb.req)
        rb.rsp.add("this.description", this.description)
        rb.rsp.add("this.name", name)
        rb.rsp.add("queryString", rb.queryString)
        rb.rsp.add("req.paramString", rb.req.paramString)
        rb.rsp.add("query tostring", "${rb.query}")
        rb.rsp.add("num docs found", "${rb.results.docList.size()}")
    }

    private fun reportZeroHit(queryString: String) = GlobalScope.launch {
        // TODO: Where do you get the core name? Surely it must be in here somewhere?
        val coreName = "films"

        HttpClient(Apache).use {
            it.post<String> {
                url("http://localhost:8989/api/zero-hit/")
                body = "{ \"queryString\":$queryString, \"count\":1, \"coreName\":$coreName }"
            }
        }
    }

    override fun prepare(rb: ResponseBuilder) {
    }

    override fun getDescription(): String {
        return "CustomSearch description."
    }
}

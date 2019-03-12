package com.sannsyn

import org.apache.solr.handler.RequestHandlerBase
import org.apache.solr.request.SolrQueryRequest
import org.apache.solr.response.SolrQueryResponse


class MyPlugin : RequestHandlerBase() {
    override fun handleRequestBody(req: SolrQueryRequest, rsp: SolrQueryResponse) {
        rsp.add("asdf", "World!")
        rsp.add("ello", "World!")
    }

    override fun getDescription(): String {
        return "My plugin description"
    }
}

package com.sannsyn.solrplugin

import org.apache.solr.handler.RequestHandlerBase
import org.apache.solr.request.SolrQueryRequest
import org.apache.solr.response.SolrQueryResponse

// Not in use currently
class MyPlugin : RequestHandlerBase() {
    override fun handleRequestBody(req: SolrQueryRequest, rsp: SolrQueryResponse) {
        rsp.add("asdf", "World!")
        rsp.add("ello", "World!")
    }

    override fun getDescription(): String {
        return "My plugin description"
    }
}

package com.sannsyn.solrplugin

import org.apache.solr.handler.component.ResponseBuilder
import org.apache.solr.handler.component.SearchComponent


class Plugin2 : SearchComponent() {
    override fun process(rb: ResponseBuilder) {
        rb.rsp.add("aaa docs found", "${rb.results.docList.size()}")
    }

    override fun prepare(rb: ResponseBuilder) {
    }

    override fun getDescription(): String {
        return "Plugin2 description"
    }
}

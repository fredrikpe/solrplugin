package com.sannsyn

import org.apache.solr.handler.component.ResponseBuilder
import org.apache.solr.handler.component.SearchComponent


class Plugin2 : SearchComponent() {
    override fun process(rb: ResponseBuilder) {
        rb.rsp.add("This is ", "Plugin 2!")
    }

    override fun prepare(rb: ResponseBuilder) {
    }

    override fun getDescription(): String {
        return "Plugin2 description"
    }
}

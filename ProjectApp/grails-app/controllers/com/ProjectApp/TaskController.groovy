package com.ProjectApp

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder

class TaskController {

    def rest

    def list (Integer projectId) {
        def resp = rest.get("${grailsApplication.config.backEnd}task/list.json?projectId=${projectId}")
        def tasks = resp.json.getAt("tasks")
        def model = [tasks : tasks]
        render view: "/task/list", model: model
    }
}

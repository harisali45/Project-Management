package com.ProjectApp

import grails.plugins.rest.client.RestBuilder

class TaskController {

    def rest

    def list (Integer projectId) {
        def resp = rest.post("${grailsApplication.config.backEnd}task/list.json") {
            header 'Accept', 'application/json'
            contentType "application/x-www-form-urlencoded"
            json projectId = projectId
        }
        def tasks = resp.json.getAt("tasks")
        def model = [tasks : tasks]
        render view: "/task/list", model: model
    }
}

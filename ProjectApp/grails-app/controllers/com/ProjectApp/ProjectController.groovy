package com.ProjectApp

import grails.plugins.rest.client.RestBuilder

class ProjectController {

    def rest


    def list () {
        //RestBuilder restBuilder = new RestBuilder()
        def resp = rest.post("${grailsApplication.config.backEnd}project/list.json")
        def projects = resp.json.getAt("projects")
        def model = [projects : projects]
        render view: "/project/list", model: model
    }
}

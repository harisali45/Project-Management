package com.ProjectApp

import grails.plugins.rest.client.RestBuilder

class ProjectController {

    def rest


    def list (Integer userId) {
        def resp = rest.get("${grailsApplication.config.backEnd}project/list?userId=${userId}")
        def projects = resp.json.getAt("projects")
        def model = [projects : projects]
        render view: "/project/list", model: model
    }

    def edit (def project) {

    }
}

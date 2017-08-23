package com.ProjectApp

import grails.plugins.rest.client.RestBuilder

class ProjectController {

    def rest
    def converterService


    def list (Integer userId) {
        def resp = rest.get("${grailsApplication.config.backEnd}project/list")
        def projects = resp.json.getAt("projects")
        def model = [projects : projects]
        render view: "/project/list", model: model
    }

    def edit (Integer projectId) {
        def resp = rest.get("${grailsApplication.config.backEnd}project/show/${projectId}")
        ProjectCommand projectCommand = new ProjectCommand()
        projectCommand.title = resp.json.title
        projectCommand.description = resp.json.description
        projectCommand.id = resp.json.id
        Map model = [project : projectCommand]
        render view: "/project/edit", model: model
    }

    def save (ProjectCommand projectCommand) {
        def resp = rest.post("${grailsApplication.config.backEnd}project/save") {
            contentType("application/x-www-form-urlencoded")
            body(converterService.convertToMap(projectCommand,["id","title","description"]))
        }
        def result = resp.json.result
        if(result.success) {
            flash.message = g.message(code:"update.successful")
        } else {
            flash.message = result.message
        }
        Map model = [projectCommand: projectCommand]
        redirect action: "edit", params: [projectId: projectCommand.id]
    }
}

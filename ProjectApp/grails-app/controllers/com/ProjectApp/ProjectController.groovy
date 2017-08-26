package com.ProjectApp

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import java.text.SimpleDateFormat

class ProjectController {

    def rest
    def converterService
    def simpleDateFormat


    def list (Integer userId) {
        def resp = rest.get("${grailsApplication.config.backEnd}project/list")
        def projects = resp.json.getAt("projects")
        def model = [projects : projects]
        render view: "/project/list", model: model
    }

    def edit (Integer projectId) {
        session.putAt("userId","1")
        ProjectCommand projectCommand = new ProjectCommand()
        if(projectId) {
            def resp = rest.get("${grailsApplication.config.backEnd}project/show?projectId=${projectId}")
            log.info "returned project for editing ${resp.json}"
            projectCommand.title = resp.json.project.title
            projectCommand.description = resp.json.project.description
            projectCommand.id = resp.json.project.id
            projectCommand.created = simpleDateFormat.parse(resp.json.project.created)
            projectCommand.owner = resp.json.owner
            log.info "${resp.json.owner}"
        }
        Map model = [project : projectCommand]
        render view: "/project/edit", model: model
    }

    def save (ProjectCommand projectCommand) {
        RestResponse resp

        if(!projectCommand.id) {
            projectCommand.owner = session.getProperty("userId")
            resp = rest.post("${grailsApplication.config.backEnd}project/save") {
                contentType("application/x-www-form-urlencoded")
                body(converterService.convertToMap(projectCommand, ["id", "title", "description","owner"]))
            }
            log.info "${resp.properties}"
            String showUrl = resp.headers.location
            projectCommand.id = Long.parseLong("${showUrl.charAt(showUrl.lastIndexOf("/")+1)}")
        } else {
            resp = rest.put("${grailsApplication.config.backEnd}project/update") {
                contentType("application/x-www-form-urlencoded")
                body(converterService.convertToMap(projectCommand, ["id", "title", "description"]))
            }
        }
        if(resp.json?.errors) {
            flash.message = ""
            resp.json.errors.each {error ->
                flash.message = "${flash.message}${error.message}\n"
            }
        } else {
            log.info "returned project: ${resp.json}"
            flash.message = g.message(code:"save.successful", args: ["Project"])
        }
        redirect action: "edit", params: [projectId: projectCommand.id]
    }
}

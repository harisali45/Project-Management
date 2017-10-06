package com.ProjectApp

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

import java.text.SimpleDateFormat

class ProjectController {

    def rest
    def converterService
    def simpleDateFormat
    def getObjectsService
    def errorService


    def list () {
        Long userId = session.userId
        def resp = rest.get("${grailsApplication.config.backEnd}project/list?id=${userId}") {
            accept("application/json")
            header('Authorization', "Bearer ${session.accessToken}")
        }
        def projects = resp.json.getAt("projects")
        def model = [projects : projects]
        render view: "/project/list", model: model
    }

    def edit (Integer projectId) {
        def usersInProject, usersNotInProject
        ProjectCommand projectCommand = new ProjectCommand()
        if(projectId) {
            def resp = rest.get("${grailsApplication.config.backEnd}project/show?projectId=${projectId}") {
                header('Authorization', "Bearer ${session.accessToken}")
            }
            projectCommand.title = resp.json.project.title
            projectCommand.description = resp.json.project.description
            projectCommand.id = resp.json.project.id
            projectCommand.created = simpleDateFormat.parse(resp.json.project.created)
            projectCommand.owner = resp.json.owner
            usersInProject = getObjectsService.getUsersInProject(projectId)
            usersNotInProject = getObjectsService.getUsersNotInProject(projectId)
        }
        Map model = [project : projectCommand,usersNotInProject: usersNotInProject, usersInProject: usersInProject ]
        render view: "/project/edit", model: model
    }

    def save (ProjectCommand projectCommand) {
        RestResponse resp

        if(!projectCommand.id) {
            projectCommand.owner = session.getProperty("userId")
            resp = rest.post("${grailsApplication.config.backEnd}project/save") {
                contentType("application/x-www-form-urlencoded")
                header('Authorization', "Bearer ${session.accessToken}")
                body(converterService.convertToMap(projectCommand, ["id", "title", "description","owner"]))
            }
            if(resp.json.result.success) {
                if(!projectCommand.id) {
                    projectCommand.id = resp.json.id
                }
                flash.message = g.message(code:"save.successful", args: ["Project"])
            } else {
                flash.message = resp.json.result.message
                flash.error = true
            }
        } else {
            resp = rest.put("${grailsApplication.config.backEnd}project/update") {
                header('Authorization', "Bearer ${session.accessToken}")
                contentType("application/x-www-form-urlencoded")
                body(converterService.convertToMap(projectCommand, ["id", "title", "description"]))
            }
            if( resp.json.errors ) {
                flash.message = ""
                resp.json.errors.each { error ->
                    flash.message = "${flash.message}${error.message} "
                }
                flash.error = true
            } else {
                flash.message = g.message(code:"save.successful", args: ["Project"])
            }
        }
        redirect action: "edit", params: [projectId: projectCommand.id]
    }

    def removeUser(Long userId, Long projectId) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>()
        form.add("userId", userId.toString())
        form.add("projectId", projectId.toString())
        RestResponse resp = rest.post("${grailsApplication.config.backEnd}project/removeUser") {
            header('Authorization', "Bearer ${session.accessToken}")
            contentType("application/x-www-form-urlencoded")
            body(form)
        }
        if(resp.json.result.success) {
            flash.message = g.message(code: "save.successful", args: ["Project"])
        } else {
            flash.message = resp.json.result.message
            flash.error = true
        }
        redirect action: "edit", params: [projectId: projectId]
    }

    def addUser(Long newUser, Long projectId) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>()
        form.add("userId", newUser.toString())
        form.add("projectId", projectId.toString())
        RestResponse resp = rest.post("${grailsApplication.config.backEnd}project/addUser") {
            header('Authorization', "Bearer ${session.accessToken}")
            contentType("application/x-www-form-urlencoded")
            body(form)
        }
        if(resp.json.result.success) {
            flash.message = g.message(code: "save.successful", args: ["Project"])
        } else {
            flash.message = resp.json.result.message
            flash.error = true
        }
        redirect action: "edit", params: [projectId: projectId]
    }
}

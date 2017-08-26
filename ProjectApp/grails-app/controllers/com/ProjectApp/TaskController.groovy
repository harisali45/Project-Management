package com.ProjectApp

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

class TaskController {

    def rest
    def converterService
    def simpleDateFormat

    def list (Integer projectId) {
        def resp = rest.get("${grailsApplication.config.backEnd}task/list?projectId=${projectId}")
        def tasks = resp.json.tasks
        def project = resp.json.project
        def model = [tasks : tasks, project: project]
        render view: "/task/list", model: model
    }

    def edit (Integer taskId, CommentCommand comment, Integer projectId) {
        TaskCommand task = new TaskCommand()
        if(taskId) {
            RestResponse resp = rest.get("${grailsApplication.config.backEnd}task/show/${taskId}")
            log.info "${resp.json}"
            task.id = resp.json.id
            task.title = resp.json.title
            task.description = resp.json.description
            task.comment = resp.json.comment
            task.created = simpleDateFormat.parse(resp.json.created)
            task.reportedBy = resp.json.reportedBy
            task.assignedTo = resp.json.assignedTo
            task.project = resp.json.project.id
        } else {
            task.project = projectId
        }
        if(!comment) {
            comment = new CommentCommand()
        }
        Map model = [task: task, comment: comment]
        render view: "/task/edit", model: model
    }

    def save (TaskCommand task) {
        RestResponse resp
        if(!task.id) {
            task.reportedBy = session.getProperty("userId")
            resp = rest.post("${grailsApplication.config.backEnd}task/save") {
                contentType("application/x-www-form-urlencoded")
                body(converterService.convertToMap(task, ["id", "title", "description","reportedBy","project"]))
            }
        } else {
            resp = rest.put("${grailsApplication.config.backEnd}task/update") {
                contentType("application/x-www-form-urlencoded")
                body(converterService.convertToMap(task, ["id", "title", "description"]))
            }
        }
        if(resp.json?.errors) {
            flash.message = ""
            resp.json.errors.each {error ->
                flash.message = "${flash.message}${error.message}\n"
            }
        } else {
            if(!task.id) {
                String showUrl = resp.headers.location
                task.id = Long.parseLong("${showUrl.charAt(showUrl.lastIndexOf("/")+1)}")
            }
            flash.message = g.message(code:"save.successful", args: ["Task"])
        }
        redirect action: "edit", params: [taskId: task.id]


    }

    def postComment (CommentCommand comment) {
        comment.user = session.getAt("userId")
        RestResponse resp = rest.post("${grailsApplication.config.backEnd}comment/save")
        if(resp.json?.errors) {

        } else {

        }
        redirect action: "edit", params: [taskId: comment.taskId, comment: comment]
    }
}

package com.ProjectApp

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

class TaskController {

    def rest
    def converterService
    def simpleDateFormat
    def errorService
    def getObjectsService

    def list (Integer projectId) {
        def projects = rest.get("${grailsApplication.config.backEnd}project/list?id=${session.userId}").json.projects
        if(!projectId) {
            projectId = projects?.get(0)?.id
        }
        def resp = rest.get("${grailsApplication.config.backEnd}task/list?projectId=${projectId}")
        def tasks = resp.json.tasks
        def project = resp.json.project

        def model = [tasks : tasks, project: project, projects: projects]
        render view: "/task/list", model: model
    }

    def edit (Integer taskId, CommentCommand comment, Long projectId) {
        TaskCommand task = new TaskCommand()
        if(taskId) {
            RestResponse resp = rest.get("${grailsApplication.config.backEnd}task/show/${taskId}")
            task.id = resp.json.id
            task.title = resp.json.title
            task.description = resp.json.description
            task.comment = getObjectsService.getComments(task.id)
            task.created = simpleDateFormat.parse(resp.json.created)
            task.reportedBy = getObjectsService.getUser(resp.json.reportedBy.id)
            task.assignedTo = resp.json.assignedTo?getObjectsService.getUser(resp.json.assignedTo.id) : resp.json.assignedTo
            task.project = resp.json.project.id
            task.status = resp.json.status
        } else {
            task.project = projectId
        }
        List usersInProject = getObjectsService.getUsersInProject(task.project)
        if(!comment) {
            comment = new CommentCommand()
        }
        Map model = [task: task, comment: comment, usersInProject: usersInProject]
        render view: "/task/edit", model: model
    }

    def save (TaskCommand task) {
        RestResponse resp
        if(!task.id) {
            task.reportedBy = session.getProperty("userId")
            def form = converterService.convertToMap(task, ["title", "description","reportedBy","project"])
            form.add("assignedTo",params.assignedTo)
            resp = rest.post("${grailsApplication.config.backEnd}task/save") {
                contentType("application/x-www-form-urlencoded")
                body(form)
            }
            if(resp.json.result.success) {
                task.id = resp.json.result.id
                flash.message = g.message(code:"save.successful", args: ["Task"])
            } else {
                flash.message = resp.json.result.message
                flash.error = true
            }
        } else {
            def form = converterService.convertToMap(task, ["id","title", "description","status"])
            form.add("assignedTo",params.assignedTo)
            resp = rest.put("${grailsApplication.config.backEnd}task/update") {
                contentType("application/x-www-form-urlencoded")
                body(form)
            }
            if(resp.json?.errors) {
            flash.message = ""
            resp.json.errors.each { error ->
                flash.message = "${flash.message}${error.message}\n"
            }
            flash.error = true
        } else {
            flash.message = g.message(code:"save.successful", args: ["Task"])
        }
            }
        redirect action: "edit", params: [taskId: task.id]
    }

    def postComment (CommentCommand comment) {
        comment.user = session.getAt("userId")
        RestResponse resp = rest.post("${grailsApplication.config.backEnd}comment/save") {
            contentType("application/x-www-form-urlencoded")
            body(converterService.convertToMap(comment, ["content", "user", "task"]))
        }
        if(!resp.json.result?.success) {
            flash.message = errorService.getErrorMsg(resp.json)
            flash.error = true
        }
        redirect action: "edit", params: [taskId: comment.task, comment: comment]
    }
}

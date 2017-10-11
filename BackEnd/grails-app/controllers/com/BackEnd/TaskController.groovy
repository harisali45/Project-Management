package com.BackEnd

import grails.converters.JSON
import grails.core.GrailsApplication
import grails.rest.RestfulController
import org.hibernate.FetchMode

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class TaskController extends RestfulController {

    static responseFormats = ['json']

    def errorService
    def notificationService

    TaskController() {
        super(Task)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Task.list(params), model:[taskCount: Task.count()]
    }

    def list() {
        Project project = Project.get(params.projectId)
        def tasks = project.task.toList()
        tasks.removeAll { task->
            !task.active
        }
        Map model = [tasks: tasks, project: project]
        render model as JSON
    }

    def show(Task task) {
        if(task.deadline) {
            task.deadline += 1
        }
        respond task
    }

    def create() {
        respond new Task(params)
    }

    def edit(Task task) {
        respond task
    }

    def save() {
        Task task = new Task(params)
        ResponseMessage responseMessage = new ResponseMessage()
        if( !task.save(flush: true) ) {
            responseMessage.message = errorService.getErrorMsg(task)
        } else {
            responseMessage.success = true
            notificationService.addNotification(task.project, "New task ${task.title} added in ${task.project.title}",
            "${grailsApplication.config.taskLink}${task.id}", grailsApplication.config.taskIcon )
        }
        Map model = [result : responseMessage, id: task.id]
        render model as JSON
    }

    def update(Task task) {
        ResponseMessage responseMessage = new ResponseMessage()
        if(!task.save(flush: true)){
            responseMessage.message = errorService.getErrorMsg(task)
        } else {
            responseMessage.success = true
        }
        Map model = [result: responseMessage]
        render model as JSON
    }

    def delete(Task task) {
        ResponseMessage responseMessage = new ResponseMessage()
        task.active = false
        task.save(flush: true)
        responseMessage.success = true
        Map model = [result: responseMessage]
        render model as JSON
    }
}

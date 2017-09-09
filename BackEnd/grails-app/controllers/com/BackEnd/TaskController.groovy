package com.BackEnd

import grails.converters.JSON
import grails.rest.RestfulController
import org.hibernate.FetchMode

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

class TaskController extends RestfulController {

    static responseFormats = ['json']

    def errorService

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
        Map model = [tasks: tasks, project: project]
        render model as JSON
    }

    def show(Task task) {
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
        if( !task.save(flush: true) ){
            responseMessage.message = errorService.getErrorMsg(task)
        } else {
            responseMessage.success = true
        }
        Map model = [result : responseMessage, id: task.id]
        render model as JSON
    }
/*
    def update(Task task) {
        if(!task.save(flush: true)){

        }

    }*/
}

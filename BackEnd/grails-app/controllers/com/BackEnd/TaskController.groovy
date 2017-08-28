package com.BackEnd

import grails.converters.JSON
import grails.rest.RestfulController
import org.hibernate.FetchMode

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


class TaskController extends RestfulController {

    //static allowedMethods = [list: "POST", save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json']

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
        log.info "task list: ${tasks.size}"
        render model as JSON
    }

    def show(Task task) {
        respond task
        /*Task.createCriteria().list {
            idEq(task.id)
            fetchMode 'comment' FetchMode.eager
        }*/

        /*Task tas = Task.get(task.id,[fetch:[comment:'eager']])
        render tas as JSON*/
        /*def tas = Task.executeQuery(
                ' select t from Task t '+
                        'left outer join fetch t.comment as comment '+
                        'where t.id=:tid ',[tid:task.id])
        render tas as JSON*/
    }

    def create() {
        respond new Task(params)
    }

    def edit(Task task) {
        respond task
        /*Task.withCriteria() {
            comment {
                fetchMode "comment", FetchMode.EAGER
            }
        }*/
    }

    @Transactional
    def update(Task task) {
        if (task == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (task.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond task.errors, view:'edit'
            return
        }

        task.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'task.label', default: 'Task'), task.id])
                redirect task
            }
            '*'{ respond task, [status: OK] }
        }
    }

    @Transactional
    def delete(Task task) {

        if (task == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        task.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'task.label', default: 'Task'), task.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

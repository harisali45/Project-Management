package com.projectmanagement

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProjectController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats= ['json','xml']

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Project.list(params), model:[projectCount: Project.count()]
    }

    def show(Project project) {
        respond project
    }

    def create() {
        respond new Project(params)
    }

    @Transactional
    def save(Project project) {
        if (project == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (project.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond project.errors, view:'create'
            return
        }

        project.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'project.label', default: 'Project'), project.id])
                redirect project
            }
            '*' { respond project, [status: CREATED] }
        }
    }

    def edit(Project project) {
        respond project
    }

    def list(){
        User haris=new User (username: "haris", name: "Haris", email: "haris@yallacompare.com")
        haris.save(flush : true, failOnError: true)
        new Project (title: "mobileApp" , owner: haris).save(failOnError:true)
        new Project (title: "broker application", owner: haris).save()
        Map model = [projects: Project.getAll()]
        respond model: model
    }

    @Transactional
    def update(Project project) {
        if (project == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (project.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond project.errors, view:'edit'
            return
        }

        project.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), project.id])
                redirect project
            }
            '*'{ respond project, [status: OK] }
        }
    }

    @Transactional
    def delete(Project project) {

        if (project == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        project.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'project.label', default: 'Project'), project.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

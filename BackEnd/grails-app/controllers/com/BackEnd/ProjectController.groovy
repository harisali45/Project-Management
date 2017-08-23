package com.BackEnd

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON

class ProjectController extends RestfulController {

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats= ['json']

    def emailService

    ProjectController() {
        super(Project)
    }

    def userProjects () {
        User user = User.get(params.userId)
        List projects = Project.findAllByContributor(user)
        Project project = Project.findByOwner(user)
        if(projects && project) {
            projects.add(project)
        } else if (project){
            projects = [project]
        }
        Map model = [projects : projects]
        render model as JSON
    }

    def list(){
        /*User haris=new User (username: "haris", name: "Haris", email: "haris@yallacompare.com")
        haris.save(flush : true, failOnError: true)
        new Project (title: "mobileApp" , owner: haris).save(failOnError:true)
        new Project (title: "broker application", owner: haris).save()*/
        Map model = [projects: Project.getAll()]
        render model as JSON
    }
}

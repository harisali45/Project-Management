package com.BackEnd

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON

class ProjectController extends RestfulController {

    static responseFormats= ['json']

    def emailService

    ProjectController() {
        super(Project)
    }

    def show() {
        Project project = Project.findById(params.projectId)
        User user = project.owner
        Map model = [project: project, owner: user]
        render model as JSON
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
        Map model = [projects: Project.getAll()]
        render model as JSON
    }

}

package com.BackEnd

import grails.rest.RestfulController
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON

class ProjectController extends RestfulController {

    static responseFormats= ['json']

    def emailService
    def errorService

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

    def getUsers(Project project) {
        List users = ProjectUser.findAllByProject(project)*.user
        Map model = [users: users]
        render model as JSON
    }

    def getUsersNotInProject(Project project) {
        String query = "select u from User u where u not in (select u from User u, ProjectUser p where p.user=u and p.project=:project)"
        List users = User.executeQuery(query, [project : project])
        Map model = [users: users]
        render model as JSON
    }

    def removeUser(Integer userId, Integer projectId) {
        ProjectUser pu = ProjectUser.findByUserAndProject(User.get(params.userId), Project.get(params.projectId))
        ResponseMessage result = new ResponseMessage()
        pu.delete(flush: true)
        result.success = true
        Map model = [result: result]
        render model as JSON
    }

    def addUser(Integer userId, Integer projectId) {
        ProjectUser projectUser = new ProjectUser(user: User.get(userId), project: Project.get(projectId))
        ResponseMessage result = new ResponseMessage()
        if(!projectUser.save(flush: true)) {
            result.message = errorService.getErrorMsg(projectUser)
        } else {
            result.success = true
        }
        Map model = [result: result]
        render model as JSON
    }

}

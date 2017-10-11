package com.BackEnd

import grails.converters.JSON
import grails.core.GrailsApplication
import grails.transaction.Transactional
import grails.util.Holders

@Transactional
class ProjectService {

    def notificationService
    def errorService

    def removeUser(Integer userId, Integer projectId) {
        User user = User.get(userId)
        Project project = Project.get(projectId)
        ProjectUser pu = ProjectUser.findByUserAndProject( user , project )
        ResponseMessage result = new ResponseMessage()
        pu.delete(flush: true)
        def tasks = Task.findAllByAssignedToAndProject(user, project)
        tasks*.assignedTo = null
        tasks*.save(flush: true)
        result.success = true
        Map model = [result: result]
        model
    }

    def addUser(Integer userId, Integer projectId) {
        User user = User.get(userId)
        Project project = Project.get(projectId)
        ProjectUser projectUser = new ProjectUser(user: user, project: project)
        ResponseMessage result = new ResponseMessage()
        if(!projectUser.save(flush: true)) {
            result.message = errorService.getErrorMsg(projectUser)
        } else {
            result.success = true
            notificationService.addNotification(project, "User ${user.name} has been added to ${project.title}", "${Holders.config.projectLink}${project.id}", Holders.config.projectIcon)
        }
        Map model = [result: result]
        model
    }
}

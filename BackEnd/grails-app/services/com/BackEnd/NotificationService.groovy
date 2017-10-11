package com.BackEnd

import grails.transaction.Transactional

@Transactional
class NotificationService {

    def addNotification(Project project, String message, String link, String icon) {
        List projectUser = ProjectUser.findAllByProject(project)
        projectUser.each { it->
            Notification notification = new Notification()
            notification.message = message
            notification.user = it.user
            notification.link = link
            notification.icon = icon
            notification.save(flush: true)
        }
    }
}

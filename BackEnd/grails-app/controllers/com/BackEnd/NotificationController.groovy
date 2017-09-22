package com.BackEnd

import grails.converters.JSON

class NotificationController {

    def getNotifications(Integer userId) {
        User user = User.get(userId)
        List notifications = Notification.findAllByUserAndRead(user, false)
        Map model = [notifications: notifications]
        render model as JSON
    }
}

package com.BackEnd

import grails.converters.JSON

class UserController {

    def errorService
    def springSecurityService
    def passwordEncoder

    def save(User user) {
        ResponseMessage responseMessage = new ResponseMessage()
        if(!user.save(flush: true)) {
            responseMessage.message = errorService.getErrorMsg(user)
        } else {
            responseMessage.success = true
        }
        Map model = [result: responseMessage]
        render model as JSON
    }

    def updateDetails() {
        User user = User.get(params.id)
        user.email = params.email
        user.name = params.name
        user.save(flush:true)
    }

    def getUser(Long userId) {
        List userList = User.createCriteria().list {
            projections {
                "id"
                "name"
                "email"
            }
            eq("id", userId)
        }
        render userList.first() as JSON
    }

    def getUserByUsername(String username) {
        List userList = User.createCriteria().list {
            projections {
                "id"
                "name"
                "email"
            }
            eq("username", username)
        }
        render userList.first() as JSON
    }

    def updatePassword() {
        User user = User.get(params.id)
        ResponseMessage result = new ResponseMessage()
        String password = params.password
        if(!passwordEncoder.isPasswordValid(user.password, password, null)) {
            result.message = g.message(code: "user.invalid.password")
        } else {
            user.password = params.newPassword
            user.save(flush:true)
            result.success = true
        }
        Map model = [result: result]
        render model as JSON
    }

}

package com.BackEnd

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

class UserController {



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
        if ( user.password != params.password ) {
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

package com.BackEnd

import grails.converters.JSON

class UserController {



    def updateDetails() {
        log.info "${params}"
        User user = User.get(params.id)
        user.email(params.email)
        user.name(params.name)
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

}

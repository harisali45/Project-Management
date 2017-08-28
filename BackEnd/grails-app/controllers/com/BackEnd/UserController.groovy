package com.BackEnd

import grails.rest.RestfulController

class UserController {



    def updateDetails() {
        log.info "${params}"
        User user = User.get(params.id)
        user.email(params.email)
        user.name(params.name)
        user.save(flush:true)


    }
}

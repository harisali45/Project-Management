package com.ProjectApp

import grails.validation.Validateable

class LoginCommand implements Validateable {

    String username
    String password

    static constraints = {
        username nullable: false
        password nullable:false
    }

}

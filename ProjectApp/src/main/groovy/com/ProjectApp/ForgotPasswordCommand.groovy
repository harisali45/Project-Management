package com.ProjectApp

import grails.validation.Validateable

class ForgotPasswordCommand implements Validateable {

    String email

    static constraints = {
        email nullable: false
    }

}

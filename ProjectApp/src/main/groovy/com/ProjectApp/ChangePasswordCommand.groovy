package com.ProjectApp

import grails.validation.Validateable

class ChangePasswordCommand implements Validateable {

    String password
    String newPassword
    String confirmPassword

    static constraints = {
        confirmPassword validator : { val, obj ->
            if(val != obj.newPassword) {
                return "user.password.mismatch"
            } else {
                return true
            }
        }
    }

}

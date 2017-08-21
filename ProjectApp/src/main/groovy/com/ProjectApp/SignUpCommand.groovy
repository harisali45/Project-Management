package com.ProjectApp

import grails.validation.Validateable

class SignUpCommand implements Validateable{

    String name
    String password
    String confirmPassword
    String email
    String username

    static constraints = {
        name nullable: false
        email nullable: false
        password nullable: false
        username nullable: false
        confirmPassword nullable: false, validator: { val, obj ->
            if(val!=obj.password) {
                return "user.password.mismatch"
            }
        }

    }

}

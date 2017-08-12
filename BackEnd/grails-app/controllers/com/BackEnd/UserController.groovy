package com.BackEnd

import grails.converters.JSON

import javax.security.auth.login.FailedLoginException


class UserController {

    def save(UserBean userBean, ResponseMessage response) {
        User user=new User()
        user.email = userBean.email
        user.name = userBean.name
        user.username = userBean.username

        if(userBean.password != userBean.confirmPassword) {
            response.message = "Passwords do not match"
            render response as JSON
            return
        }

        Login login = new Login(user: user, password: userBean.password)

        if(user.save(flush: true) && login.save(flush: true)) {
            response.success = true
            response.message = "User created successfully"
        } else {
            response.message = "User creation failed due to: ${user.errors.allErrors.join('\n')} \n${login.errors.allErrors.join('\n')}"
        }

        render response as JSON

    }

    def login(String username, String password){

        User user = User.findByUsername(username)

        try {
            if (!user)
                throw FailedLoginException

            Login login = Login.findByUser(user)

            if (login.password != password)
                throw FailedLoginException

        } catch (FailedLoginException) {
            ResponseMessage response = new ResponseMessage(message: g.message(code: "login.failed"))
            render response as JSON
            return
        }

    }



}

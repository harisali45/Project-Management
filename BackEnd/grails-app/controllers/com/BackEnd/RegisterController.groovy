package com.BackEnd

import grails.converters.JSON

import javax.security.auth.login.FailedLoginException


class RegisterController {

    def save(UserCommand userCommand, ResponseMessage response) {
        User user=new User()
        user.email = userCommand.email
        user.name = userCommand.name
        user.username = userCommand.username

        if(userCommand.password != userCommand.confirmPassword) {
            response.message = "Passwords do not match"
            render response as JSON
            return
        }

        Login login = new Login(user: user, password: userCommand.password)

        if(user.save(flush: true) && login.save(flush: true)) {
            response.success = true
            response.message = "User created successfully"
        } else {
            response.message = "User creation failed due to: ${user.errors.allErrors.join('\n')} \n${login.errors.allErrors.join('\n')}"
        }

        Map model = [result: response]
        render model as JSON

    }

    def login(){

        String username = params.username
        String password = params.password
        User user = User.findByUsername(username)
        ResponseMessage response = new ResponseMessage();

        try {
            if (!user)
                throw FailedLoginException

            if (user.password != password)
                throw FailedLoginException

            response.success = true

        } catch (FailedLoginException) {

            response.message = g.message(code: "user.login.failed")
            response.success = false

        }

        Map model = [result: response]
        render model as JSON

    }



}

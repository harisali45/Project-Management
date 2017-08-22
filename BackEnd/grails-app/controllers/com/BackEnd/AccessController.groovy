package com.BackEnd

import grails.converters.JSON
import org.apache.commons.lang.RandomStringUtils

import javax.security.auth.login.FailedLoginException


class AccessController {

    def emailService

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

        Map model = [result: response, user: user]
        render model as JSON

    }

    def forgotPassword() {
        ResponseMessage response = new ResponseMessage()

        User user = User.findByEmail(params.email)

        if(!user) {
            response.message = g.message(code: "user.email.notfound")
        } else {
            String password = RandomStringUtils.random(9,true, true)
            user.password = password
            user.save(flush:true)
            Email email = new Email(toAddress: params.email, subject: "ProjectManagement: Password Reset",
            content: "Password reset successful for user ${user.username}, your new password is ${password}")
            emailService.sendEmail(email)
            response.success = true
        }

        Map model = [result: response]
        render model as JSON
    }
}

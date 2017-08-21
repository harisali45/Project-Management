package com.ProjectApp

class UserController {

    def rest
    def converterService

    def showSignUp(SignUpCommand signUpCommand) {
        def model = [signUpCommand : signUpCommand]
        render view: "/sign-up", model: model
    }

    def signUp(SignUpCommand signUpCommand) {
        if(!signUpCommand.validate()) {
            def model = [signUpCommand : signUpCommand]
            redirect action: "showSignUp", params: model
            return
        }

        def resp = rest.post("${grailsApplication.config.backEnd}user") {
            contentType("application/x-www-form-urlencoded")
            body(converterService.convertToMap(signUpCommand, ["name","password","username","email"]))
        }
        def result = resp.json.getAt("result")
        resp.properties.each { prop, val ->
            log.info "${prop} : ${val}"
        }
        /*if(result*//*.success*//*) {
            flash.message = g.message(code: "signUp.success")
            render controller: "login", action: "index"
        } else {
     //       flash.message = result.message
            flash.message = "Something's wrong"
            def model = [signUpCommand : signUpCommand]
            redirect action: "showSignUp", params: model
        }*/

        def model = [signUpCommand : signUpCommand]
        redirect action: "showSignUp", params: model

    }

    def showForgotPassword (ForgotPasswordCommand forgotPasswordCommand) {
        Map model = [forgotPasswordCommand: forgotPasswordCommand]
        render view: "/forgot-password", model: forgotPasswordCommand
    }


    def forgotPassword (ForgotPasswordCommand forgotPasswordCommand) {
        if(!forgotPasswordCommand.validate()) {

            redirect action: showForgotPassword(forgotPasswordCommand: forgotPasswordCommand)
        }

        def result = rest.post("${grailsApplication.config.backEnd}user/forgotPassword") {
            contentType("application/x-www-form-urlencoded")
            body(converterService.convertToMap(forgotPasswordCommand, ["email"]))
        }

        if(result.success) {
            flash.message = g.message(code:"user.password.reset")

        } else {
            flash.message = result.message
            redirect action: showForgotPassword(forgotPasswordCommand: forgotPasswordCommand)
        }
    }
}
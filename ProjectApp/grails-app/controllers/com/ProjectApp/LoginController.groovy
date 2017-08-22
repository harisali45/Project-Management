package com.ProjectApp

class LoginController {

    def rest
    def converterService

    def index (LoginCommand loginCommand) {

        Map model = [loginCommand: loginCommand]
        render view: "/sign-in", model: model

    }

    def login(LoginCommand loginCommand) {

        def props = ['username','password']

        def form = converterService.convertToMap(loginCommand,props)

        def resp = rest.post("${grailsApplication.config.backEnd}access/login.json") {
            contentType("application/x-www-form-urlencoded")
            body(form)
        }
        def result = resp.json.getAt("result")
        if (result.success) {
            def user = resp.json.user
            session.putAt("name",user.name)
            session.putAt("username",loginCommand.username)
            session.putAt("email",user.email)
            redirect controller: "project", action: "list", params: [userId: user.id]
        } else {
            flash.message = result.message
            redirect action: "index", params: [loginCommand: loginCommand]
        }
    }

    def logout () {
        session.invalidate()
        flash.message=g.message(code:"user.logout")
        redirect action: "index"
    }

    def showForgotPassword (ForgotPasswordCommand forgotPasswordCommand) {
        Map model = [forgotPasswordCommand: forgotPasswordCommand]
        render view: "/forgot-password", model: forgotPasswordCommand
    }


    def forgotPassword (ForgotPasswordCommand forgotPasswordCommand) {
        Map model = [ forgotPasswordCommand : forgotPasswordCommand ]
        if(!forgotPasswordCommand.validate()) {
            redirect action: "showForgotPassword", params: model
            return
        }

        def resp = rest.post("${grailsApplication.config.backEnd}access/forgotPassword") {
            contentType("application/x-www-form-urlencoded")
            body(converterService.convertToMap(forgotPasswordCommand, ["email"]))
        }

        def result = resp.json.result

        if(result.success) {
            flash.message = g.message(code:"forgotPassword.success")
        } else {
            flash.message = result.message
        }
        log.info "${result}"
        redirect action: "showForgotPassword", params: model
    }
}
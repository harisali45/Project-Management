package com.ProjectApp

import grails.plugins.rest.client.RestResponse

class LoginController {

    def rest
    def converterService
    def getObjectsService

    def index (LoginCommand loginCommand) {

        Map model = [loginCommand: loginCommand]
        render view: "/sign-in", model: model

    }

    def login(LoginCommand loginCommand) {

        def props = ['username','password']

        def form = converterService.convertToMap(loginCommand,props)

        RestResponse resp = rest.post("${grailsApplication.config.backEnd}api/login") {
            contentType("application/json")
            body("{'username':'${loginCommand.username}','password':'${loginCommand.password}'")
        }
        log.info "${resp.properties}"
        //def result = resp.json.getAt("result")
        if (resp.headers.Location.toString().contains("error")) {
            flash.message = g.message(code: "login.invalid")
            redirect action: "index", params: [loginCommand: loginCommand]
        } else {
            session.putAt("backEndCookie", resp.headers.SET_COOKIE)
            def user = getObjectsService.getUserByUsername(loginCommand.username , loginCommand.password)
            session.putAt("name",user.name)
            session.putAt("username",loginCommand.username)
            session.putAt("email",user.email)
            session.putAt("userId",user.id)
            redirect controller: "project", action: "list", params: [userId: user.id]
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
        redirect action: "showForgotPassword", params: model
    }
}

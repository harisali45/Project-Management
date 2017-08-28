package com.ProjectApp

import grails.plugins.rest.client.RestResponse
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

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

        def resp = rest.post("${grailsApplication.config.backEnd}user/save") {
            contentType("application/x-www-form-urlencoded")
            body(converterService.convertToMap(signUpCommand, ["name","password","username","email"]))
        }

        if ( resp.json ) {
            resp.json.errors.each { error ->
                log.info "message: ${error.message}"
                flash.message = error.message
            }
            redirect action: "showSignUp", params: [signUpCommand: signUpCommand]
        } else {
            flash.message = g.message(code: "signUp.success")
            redirect controller: "login", action: "index"
        }
    }

    def show() {
        SignUpCommand user = new SignUpCommand()
        user.name = session.getAt("name")
        user.email = session.getAt("email")
        Map model = [user: user]
        render view: '/user', model: model
    }

    def save(SignUpCommand user) {
        RestResponse resp = rest.post("${grailsApplication.config.backEnd}user/updateDetails") {
            contentType("application/x-www-form-urlencoded")
            body(converterService.convertToMap(user, ["name","email"]).add("id", "${session.userId}"))
        }
        if ( resp.json?.errors ) {
            resp.json.errors.each { error ->
                log.info "message: ${error.message}"
                flash.message = error.message
            }
            redirect action: "show", params: [changePasswordCommand: changePasswordCommand]
        } else {
            session.putAt("name",user.name)
            session.putAt("email",user.email)
            flash.message = g.message(code: "save.successful", params: ["Changes"])
            redirect action: "show"
        }
    }

    def changePassword(ChangePasswordCommand changePasswordCommand) {

        if(!changePasswordCommand.validate()) {

        }
        RestResponse resp = rest.put("${grailsApplication.config.backEnd}user/update") {
            contentType("application/x-www-form-urlencoded")
            body(converterService.convertToMap(changePasswordCommand, ["password"]).add("id", "${session.userId}"))
        }
        if ( resp.json?.errors ) {
            resp.json.errors.each { error ->
                log.info "message: ${error.message}"
                flash.message = error.message
            }
            redirect action: "show", params: [changePasswordCommand: changePasswordCommand]
        } else {
            flash.message = g.message(code: "user.password.changed")
            redirect action: "show"
        }

    }

    def invite(String newUserEmail, String newUserName) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>()
        form.add("newUserEmail", newUserEmail)
        form.add("newUserName", newUserName)
        form.add("referrerId" , ''+session.userId)
        RestResponse resp = rest.put("${grailsApplication.config.backEnd}access/invite") {
            contentType("application/x-www-form-urlencoded")
            body(form)
        }
        if ( resp.json?.errors ) {
            resp.json.errors.each { error ->
                log.info "message: ${error.message}"
                flash.message = error.message
            }
            redirect action: "show"
        } else {
            flash.message = g.message(code: "user.invite.sent")
            redirect action: "show"
        }
    }
}
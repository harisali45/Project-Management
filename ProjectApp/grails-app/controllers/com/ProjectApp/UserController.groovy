package com.ProjectApp

import grails.plugins.rest.client.RestResponse
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

class UserController {

    def rest
    def converterService
    def errorService

    def showSignUp(SignUpCommand signUpCommand) {
        def model = [signUpCommand : signUpCommand]
        render view: "/sign-up", model: model
    }

    def signUp(SignUpCommand signUpCommand) {
        if(!signUpCommand.validate()) {
            def model = [signUpCommand : signUpCommand]
            //flash.message = errorService.getErrorMsg(signUpCommand)
            redirect action: "showSignUp", params: model
            return
        }

        def resp = rest.post("${grailsApplication.config.backEnd}user/save") {
            contentType("application/x-www-form-urlencoded")
            body(converterService.convertToMap(signUpCommand, ["name","password","username","email"]))
        }

        if ( resp.json?.result.success ) {
            flash.message = g.message(code: "signUp.success")
            redirect controller: "login", action: "auth"
        } else {
            flash.message = resp.json.result.message
            redirect action: "showSignUp", params: [signUpCommand: signUpCommand]
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
        def form = converterService.convertToMap(user, ["name","email"])
        form.add("id", "${session.userId}".toString())
        RestResponse resp = rest.post("${grailsApplication.config.backEnd}user/updateDetails") {
            header('Authorization', "Bearer ${session.accessToken}")
            contentType("application/x-www-form-urlencoded")
            body(form)
        }
        if ( resp.json?.errors ) {
            resp.json.errors.each { error ->
                flash.message = error.message
            }
            redirect action: "show", params: [changePasswordCommand: changePasswordCommand]
        } else {
            session.putAt("name",user.name)
            session.putAt("email",user.email)
            flash.message = g.message(code: "save.successful", args: ["Changes"])
            redirect action: "show"
        }
    }

    def changePassword(ChangePasswordCommand changePasswordCommand) {

        if(!changePasswordCommand.validate()) {
            flash.message = g.message(code: "default.check.errors")
            flash.error = true
            redirect action: "show", params: [changePasswordCommand: changePasswordCommand]
            return
        }
        def form = converterService.convertToMap(changePasswordCommand, ["password", "newPassword"])
        form.add("id", "${session.userId}".toString())
        RestResponse resp = rest.post("${grailsApplication.config.backEnd}user/updatePassword") {
            header('Authorization', "Bearer ${session.accessToken}")
            contentType("application/x-www-form-urlencoded")
            body(form)
        }
        if ( resp.json.result.success ) {
            flash.message = g.message(code: "user.password.changed")
            redirect action: "show"
        } else {
            flash.message = resp.json.result.message
            flash.error = true
            redirect action: "show", params: [changePasswordCommand: changePasswordCommand]
        }

    }

    def invite(String newUserEmail, String newUserName) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>()
        form.add("newUserEmail", newUserEmail)
        form.add("newUserName", newUserName)
        form.add("referrerId" , ''+session.userId)
        RestResponse resp = rest.put("${grailsApplication.config.backEnd}access/invite") {
            header('Authorization', "Bearer ${session.accessToken}")
            contentType("application/x-www-form-urlencoded")
            body(form)
        }
        if ( resp.json?.errors ) {
            resp.json.errors.each { error ->
                flash.message = error.message
            }
            redirect action: "show"
        } else {
            flash.message = g.message(code: "user.invite.sent")
            redirect action: "show"
        }
    }
}
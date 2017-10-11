package com.ProjectApp

class AccessController {

    def rest
    def converterService

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

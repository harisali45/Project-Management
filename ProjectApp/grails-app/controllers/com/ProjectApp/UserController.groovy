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

}
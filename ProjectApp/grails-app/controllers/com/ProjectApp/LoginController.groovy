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

        def resp = rest.post("${grailsApplication.config.backEnd}user/login.json") {
            contentType("application/x-www-form-urlencoded")
            body(form)
        }
        def result = resp.json.getAt("response")
        if (result.success) {
            redirect controller: "project", action: "list"
        } else {
            flash.message = result.message
            redirect action: "index", params: [loginCommand: loginCommand]
        }
    }
}

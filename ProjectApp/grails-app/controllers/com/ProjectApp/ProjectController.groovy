package com.ProjectApp

class ProjectController {


    def list () {
        def resp = rest.post(restAddress)
        def projects = resp.json
        def model = [projects : projects]
        render view: "project/list", model: model
    }
}

package projectmgmt

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "project", action: "list")

        "/signUp"(view:"/sign-up")
        "/showSignUp"(controller: "user", action: "showSignUp")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

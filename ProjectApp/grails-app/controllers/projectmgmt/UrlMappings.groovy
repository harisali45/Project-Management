package projectmgmt

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "project", action: "list")

        name login: "/login" (
            controller: "login",
            action: "index"
        )

        "/signUp"(view:"/sign-up")
        "/user/login"(controller: "user", action: "login")
        "/forgotPassword"(view:"/forgot-password")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

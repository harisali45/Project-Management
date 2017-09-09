package projectmgmt

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/sign-in.gsp")

        name login: "/login" (
            controller: "login",
            action: "index"
        )

        "/signUp"(view:"/sign-up")
        "/showSignUp"(controller: "user", action: "showSignUp")
        //"/user/login"(controller: "login", action: "login")
        "/forgotPassword"(view:"/forgot-password")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

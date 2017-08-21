package backend

import com.BackEnd.User

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/user"(resource: User)

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

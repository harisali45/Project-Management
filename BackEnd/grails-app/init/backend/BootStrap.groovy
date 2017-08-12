package backend

import com.BackEnd.User
import com.BackEnd.Project

class BootStrap {

    def init = { servletContext ->
        User haris=new User (username: "haris", name: "Haris",email: "haris@yallacompare.com")
        haris.save(flush : true, failOnError: true)
        new Project (title: "mobileApp" , owner: haris).save(flush : true, failOnError: true)
        new Project (title: "broker application", owner: haris).save(flush : true)

    }
    def destroy = {
    }
}

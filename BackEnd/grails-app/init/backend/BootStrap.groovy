package backend

import com.BackEnd.User
import com.BackEnd.Project
import com.BackEnd.Task

class BootStrap {

    def init = { servletContext ->
        User haris=new User (username: "haris", name: "Haris",email: "haris@yallacompare.com")
        haris.save(flush : true, failOnError: true)
        Project mobileApp = new Project (title: "mobileApp" , owner: haris)
        mobileApp.save(flush : true, failOnError: true)
        new Project (title: "broker application", owner: haris).save(flush : true)

        Task task = new Task (title: "planning", project: mobileApp, reportedBy: haris, assignedTo: haris)
        task.save(flush: true, failOnError: true)

    }
    def destroy = {
    }
}

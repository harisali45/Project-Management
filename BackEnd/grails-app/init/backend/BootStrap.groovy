package backend

import com.BackEnd.*

class BootStrap {

    def init = { servletContext ->
        User haris=new User (username: "haris", name: "Haris",email: "haris@yallacompare.com", password: "haris")
        haris.save(flush : true, failOnError: true)
        Project mobileApp = new Project (title: "mobileApp" , owner: haris, description: "mibile app")
        mobileApp.save(flush : true, failOnError: true)
        new Project (title: "broker application", owner: haris).save(flush : true)

        Task task = new Task (title: "planning", project: mobileApp, reportedBy: haris, assignedTo: haris)
        task.save(flush: true, failOnError: true)

        Comment comment = new Comment(content: "we need to get started on this as soon as possible",task:task, user: haris)
        comment.save(flush: true, failOnError: true)

        mobileApp.addToTask(task)
        mobileApp.save(flush:true)

    }
    def destroy = {
    }
}

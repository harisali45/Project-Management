package backend

import com.BackEnd.*

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        User haris=new User (username: "haris", name: "Haris",email: "haris@yallacompare.com", password: "haris")
        haris.save(flush : true, failOnError: true)
        User bob=new User (username: "bob", name: "Bob",email: "bob@yallacompare.com", password: "bob")
        bob.save(flush : true, failOnError: true)
        User matthew=new User (username: "matthew", name: "Matthew",email: "matthew@yallacompare.com", password: "matthew")
        matthew.save(flush : true, failOnError: true)

        Project mobileApp = new Project (title: "mobileApp" , owner: haris, description: "mobile app")
        mobileApp.save(flush : true, failOnError: true)
        new Project (title: "broker application", owner: haris).save(flush : true)

        Task task = new Task (title: "planning", project: mobileApp, reportedBy: haris, assignedTo: haris)
        task.save(flush: true, failOnError: true)

        Comment comment = new Comment(content: "we need to get started on this as soon as possible",task:task, user: haris)
        comment.save(flush: true, failOnError: true)

        mobileApp.addToTask(task)
        mobileApp.save(flush:true)

        new ProjectUser(project: mobileApp, user: haris).save(flush:true)
        new ProjectUser(project: mobileApp, user: bob).save(flush:true)
        new ProjectUser(project: mobileApp, user: matthew).save(flush:true)

        Role sr = new Role(authority: "ROLE_USER")
        sr.save(flush: true, failOnError: true)
        new UserRole(user: haris, role: sr ).save(flush: true, failOnError: true)

        springSecurityService.clearCachedRequestmaps()
    }
    def destroy = {
    }
}

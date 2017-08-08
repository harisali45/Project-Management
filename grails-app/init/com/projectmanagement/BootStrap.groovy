package com.projectmanagement

class BootStrap {

    def init = { servletContext ->
        User haris=new User (username: "haris", name: "Haris")
        haris.save(flush : true)
        new Project (title: "mobileApp" , owner: haris).save(flush : true)
        new Project (title: "broker application", owner: haris).save(flush : true)

    }
    def destroy = {
    }
}

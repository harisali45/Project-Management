package com.domains

class User {

    String username
    String name
    String email
    Integer userStatus = 1
    Integer deleteFlag = 0

    static hasMany = [project : Project, reportedBy : Task, assignedTo: Task, comment : Comment]
    static mappedBy = [
        reportedBy : 'reportedBy',
        assignedTo : 'assignedTo'
    ]
    static hasOne = [login: Login]

    static constraints = {
        email email:true, unique: true, nullable: false
        name nullable: false
        username nullable:false, unique: true
    }

    static mapping = {
        userStatus defaultValue : 1
        deleteFlag defaultValue : 0
    }
}

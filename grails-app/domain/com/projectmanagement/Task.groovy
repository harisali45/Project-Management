package com.projectmanagement

class Task {

    String title
    String description
    User reportedBy
    User assignedTo
    Date created
    Date updated
    Integer deleteFlag

    static hasMany = [comment: Comment]
    static hasOne = [reportedBy : User, assignedTo : User]

    static belongsTo = [Project]

    static constraints = {
        title nullable: false
        created default: new Date()
    }

    static mapping = {
        deleteFlag defaultValue : 0
    }
}

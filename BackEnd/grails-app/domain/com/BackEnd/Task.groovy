package com.BackEnd

class Task {

    String title
    String description
    com.BackEnd.User reportedBy
    com.BackEnd.User assignedTo
    Date created
    Date updated
    Integer deleteFlag

    static hasMany = [comment: Comment]
    static hasOne = [reportedBy: com.BackEnd.User, assignedTo: com.BackEnd.User]

    static belongsTo = [Project]

    static constraints = {
        title nullable: false
        created default: new Date()
    }

    static mapping = {
        deleteFlag defaultValue : 0
    }
}

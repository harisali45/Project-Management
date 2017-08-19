package com.BackEnd

class Task {

    String title
    String description
    com.BackEnd.User reportedBy
    com.BackEnd.User assignedTo
    Date created
    Date updated
    Integer deleteFlag = 0
    //Project project

    static hasMany = [comment: Comment]
    static hasOne = [reportedBy: com.BackEnd.User, assignedTo: com.BackEnd.User, project: Project]

    static belongsTo = [project : Project]

    static constraints = {
        title nullable: false
        description nullable: true
        created nullable: true
        updated nullable: true
    }

    static mapping = {
        deleteFlag defaultValue : 0
    }
}

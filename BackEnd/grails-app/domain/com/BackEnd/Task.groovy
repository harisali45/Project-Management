package com.BackEnd

class Task {

    String title
    String description
    com.BackEnd.User reportedBy
    com.BackEnd.User assignedTo
    Date created = new Date()
    Date updated = new Date()
    Integer deleteFlag = 0
    //Project project

    static hasMany = [comment: Comment]
    static hasOne = [reportedBy: com.BackEnd.User, assignedTo: com.BackEnd.User, project: Project]

    static belongsTo = [project : Project]

    static constraints = {
        title nullable: false
        description nullable: true
        created nullable: false
        updated nullable: true
        assignedTo nullable: true
    }

    static mapping = {
        deleteFlag defaultValue : 0
        comment fetch: 'eager'
    }
}

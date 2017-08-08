package com.projectmanagement

class Comment {

    User user
    Task task
    Date created
    String description
    Integer deleteFlag

    static belongsTo = [task : Task]
    static hasOne = [user : User, task : Task]

    static constraints = {
        description nullable: false
    }

    static mapping = {
        created defaultValue : "now()"
        deleteFlag defaultValue : 0
    }
}

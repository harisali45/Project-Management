package com.BackEnd

class Comment {

    com.BackEnd.User user
    com.BackEnd.Task task
    Date created
    String description
    Integer deleteFlag

    static belongsTo = [task : com.BackEnd.Task]
    static hasOne = [user: com.BackEnd.User, task: com.BackEnd.Task]

    static constraints = {
        description nullable: false
    }

    static mapping = {
        created defaultValue : "now()"
        deleteFlag defaultValue : 0
    }
}

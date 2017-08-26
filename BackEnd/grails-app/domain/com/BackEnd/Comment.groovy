package com.BackEnd

class Comment {

    com.BackEnd.User user
    com.BackEnd.Task task
    Date created = new Date()
    String content
    Integer deleteFlag = 0

    static belongsTo = [task : com.BackEnd.Task]
    static hasOne = [user: com.BackEnd.User, task: com.BackEnd.Task]

    static constraints = {
        content nullable: false
    }

    static mapping = {
        created defaultValue : "now()"
        deleteFlag defaultValue : 0
    }
}

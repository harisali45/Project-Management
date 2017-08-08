package com.projectmanagement

class Project {

    String title
    User owner
    Date created = new Date()
    String description
    Task task
    Integer deleteFlag = 0

    static hasMany = [task: Task]
    static hasOne = [owner: User]

    static constraints = {
        title nullable: false
        created nullable: false
        description nullable: true
        task nullable: true
        owner nullable: false
        deleteFlag nullable: false
    }

    static mapping = {
        created defaultValue: "now()"
        deleteFlag defaultValue : 0
    }
}

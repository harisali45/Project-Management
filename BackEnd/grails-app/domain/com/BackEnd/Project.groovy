package com.BackEnd

class Project {

    String title
    com.BackEnd.User owner
    Date created = new Date()
    String description
    //com.BackEnd.Task task
    Integer deleteFlag = 0

    static hasMany = [task: com.BackEnd.Task/*, contributor: User*/]
    static hasOne = [owner: com.BackEnd.User]
    //static belongsTo = [owner: User]

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

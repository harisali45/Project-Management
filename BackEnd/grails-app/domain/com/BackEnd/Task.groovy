package com.BackEnd

import java.text.DateFormat
import java.text.SimpleDateFormat

class Task {

    String title
    String description
    com.BackEnd.User reportedBy
    com.BackEnd.User assignedTo
    Date created = new Date()
    Date updated = new Date()
    Integer deleteFlag = 0
    String status = TaskStatusEnum.ONGOING
    Date deadline
    boolean active = true

    static hasMany = [comment: Comment]
    static hasOne = [reportedBy: com.BackEnd.User, assignedTo: com.BackEnd.User, project: Project]

    static belongsTo = [project : Project]

    static constraints = {
        title nullable: false
        description nullable: true
        created nullable: false
        updated nullable: true
        assignedTo nullable: true
        comment nullable: true
        deadline nullable: true
    }

    static mapping = {
        deleteFlag defaultValue : 0
    }

    /*void setDeadline(String deadline){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy")
        this.deadline = simpleDateFormat.parse(deadline)
    }*/
}

enum TaskStatusEnum {
    ONGOING ,
    COMPLETED ,
    CANCELLED
}


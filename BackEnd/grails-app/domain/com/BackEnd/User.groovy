package com.BackEnd

class User {

    String username
    String name
    String email
    UserStatusEnum userStatus = UserStatusEnum.active
    Integer deleteFlag = 0
    String password

    static hasMany = [project: Project, reportedBy: Task, assignedTo: Task, comment: Comment/*, contributor: Project*/]
    static mappedBy = [
        reportedBy : 'reportedBy',
        assignedTo : 'assignedTo',
        /*contributor: 'contributor',
        project: 'owner'*/
    ]

    static constraints = {
        email email:true, unique: true, nullable: false
        name validator: { val, obj ->
            if(obj.userStatus != com.BackEnd.UserStatusEnum.initiated && !(val)) {
                return ["default.required", "Name"]
            }
            return true
        }
        username nullable: true, unique: true, validator: { val, obj ->
            if(obj.userStatus != com.BackEnd.UserStatusEnum.initiated && !(val)) {
                return ["default.required", "Username"]
            }
            return true
        }
        password nullable: true, validator: { val, obj ->
            if(obj.password != com.BackEnd.UserStatusEnum.initiated && !(val)) {
                return ["default.required", "Password"]
            }
            return true
        }
    }

    static mapping = {
        userStatus defaultValue : 1
        deleteFlag defaultValue : 0
    }
}

public enum UserStatusEnum {
    initiated (0),
    active (1),
    inactive (2)

    Integer status

    UserStatusEnum (Integer status) {
        this.status = status
    }
}
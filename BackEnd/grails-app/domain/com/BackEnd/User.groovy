package com.BackEnd

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User {

    String username
    String name
    String email
    String password
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    static hasMany = [project: Project, reportedBy: Task, assignedTo: Task, comment: Comment/*, contributor: Project*/]
    static mappedBy = [
        reportedBy : 'reportedBy',
        assignedTo : 'assignedTo',
        /*contributor: 'contributor',
        project: 'owner'*/
    ]

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        email email:true, unique: true, nullable: false
        name nullable: true
        username nullable: true, unique: true
        password nullable: true, password: true
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
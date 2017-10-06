package com.ProjectApp

import grails.validation.Validateable

class TaskCommand implements Validateable {

    Long id
    String title
    String description
    def reportedBy
    def assignedTo
    Date created
    Date updated
    List comment
    Long project
    String status
    Date deadline

    static constraints = {
        assignedTo nullable: true
        description nullable: true
        deadline nullable: true
    }

}

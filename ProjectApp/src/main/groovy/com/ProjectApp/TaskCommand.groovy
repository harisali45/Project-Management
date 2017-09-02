package com.ProjectApp

class TaskCommand {

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

    static constraints = {
        assignedTo nullable: true
        description nullable: true
    }
}

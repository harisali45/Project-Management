package com.ProjectApp

import grails.validation.Validateable

class ProjectCommand implements Validateable {
    String title
    Long id
    String description
    Date created
    def owner

    static constraints = {
        title nullable: false
    }

}

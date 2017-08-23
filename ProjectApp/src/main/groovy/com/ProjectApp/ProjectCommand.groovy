package com.ProjectApp

import grails.validation.Validateable

class ProjectCommand implements Validateable {
    String title
    Integer id
    String description

    static constraints = {
        title nullable: false
    }

}

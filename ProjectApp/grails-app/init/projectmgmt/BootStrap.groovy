package projectmgmt

import com.ProjectApp.Role
import grails.plugin.springsecurity.SpringSecurityUtils

class BootStrap {

    def init = { servletContext ->
        Role role = new Role(authority: "ROLE_USER")
        role.save(flush: true)
    }
    def destroy = {
    }
}

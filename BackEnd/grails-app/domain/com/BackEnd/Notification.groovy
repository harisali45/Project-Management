package com.BackEnd

class Notification {

    String message
    Boolean read = false
    User user
    String link
    String icon

    static constraints = {
        message nullable: false
        user nullable: false
        read nullable: false
        link nullable: true
        icon nullable: true
    }
}

package com.BackEnd

class Notification {

    String message
    Boolean read = false
    User user

    static constraints = {
        message nullable: false
        user nullable: false
        read nullable: false
    }
}

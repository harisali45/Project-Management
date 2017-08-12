package com.domains

import java.security.MessageDigest

class Login {

    User user
    String password

    static hasOne = [user: User]
    static belongsTo = [user: User]

    static constraints = {
        user nullable: false
        password nullable:false
    }

    def setPassword(String password) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256")
        digest.update(password.getBytes("UTF-8"))
        this.password = digest.digest().toString()
    }
}

package com.BackEnd

import grails.rest.RestfulController

class UserController extends RestfulController {

    static responseFormats = ['json']

    UserController() {
        super (User)
    }
}

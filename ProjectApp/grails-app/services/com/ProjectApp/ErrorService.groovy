package com.ProjectApp

import grails.transaction.Transactional

class ErrorService {

    def getErrorMsg(object) {
        String errorMsg = ""
        object.errors.each { error->
            errorMsg = "${errorMsg}\n${error.message}"
        }
        errorMsg
    }
}

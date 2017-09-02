package com.BackEnd

class ErrorService {

    def getErrorMsg(def object) {
        String errorMsg = ""
        object.errors.allErrors.each { error->
            errorMsg = "${errorMsg} ; ${error.defaultMessage}"
        }
        errorMsg
    }
}

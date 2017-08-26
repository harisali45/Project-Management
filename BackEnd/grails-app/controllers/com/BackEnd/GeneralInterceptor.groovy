package com.BackEnd


class GeneralInterceptor {

    public GeneralInterceptor() {
        //matchAll()
        match controller: "project"
        match controller: "task"
    }

    boolean before() {
        log.debug "BEFORE params: ${params}"
        true
    }

    boolean after() {
        log.debug "AFTER params: ${params}"
        true
    }

    void afterView() {
        // no-op
    }
}

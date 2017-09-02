package com.BackEnd

import grails.converters.JSON
import grails.rest.RestfulController

class CommentController extends RestfulController{

    static responseFormats = ['json']

    CommentController() {
        super(Comment)
    }

    def getComments(Long taskId) {
        def comments = Comment.findAllByTask(Task.get(taskId), [sort: "created", order: "desc"])
        Map model = [comments : comments]
        render model as JSON
    }
}

package com.BackEnd

import grails.converters.JSON

class CommentController {

    def getComments(Task task) {
        def comments = Comment.findAllByTask(task)
        Map model = [comments : comments]
        render model as JSON
    }

    def save(Comment comment) {
        ResponseMessage message = new ResponseMessage()
        if(comment.save(flush: true)) {
            message.message = g.message(code: "default.saved")
            message.success = true
        }
        else
            message.message = g.message(code: "default.failed")
        render message as JSON
    }

}

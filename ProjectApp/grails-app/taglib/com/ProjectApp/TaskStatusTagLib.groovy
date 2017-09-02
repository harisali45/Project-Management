package com.ProjectApp

class TaskStatusTagLib {
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "taskStatus"

    def showBadge = { attrs ->

        String span = "<span class='${attrs.class} badge "
        switch(attrs.status) {
            case "ONGOING": span += "bg-orange"
                            break
            case "COMPLETED": span += "bg-teal"
                            break
            case "CANCELLED": span += "bg-pink"
                            break
        }

        span = "${span}'>${attrs.status}</span>"
        out << span
    }
}

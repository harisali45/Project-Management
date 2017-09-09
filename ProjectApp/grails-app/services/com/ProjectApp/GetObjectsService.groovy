package com.ProjectApp

import grails.plugins.rest.client.RestResponse
import grails.util.Holders


class GetObjectsService {

    def rest
    def simpleDateFormat

    def getUser(Long id) {
        RestResponse resp = rest.get("${Holders.config.backEnd}/user/getUser?userId=${id}")
        def user = [
                id: resp.json.id,
                name: resp.json.name,
                email: resp.json.email
                ]
        user
    }

    def getUserByUsername(String username, String password ) {

        RestResponse resp = rest.get("${Holders.config.backEnd}/user/getUserByUsername?username=${username}") {
            auth(username, password)
        }
        def user = [
                id: resp.json.id,
                name: resp.json.name,
                email: resp.json.email
        ]
        user
    }

    def getUsersInProject (Long projectId) {
        RestResponse resp = rest.get("${Holders.config.backEnd}project/getUsers?id=${projectId}")
        resp.json.users
    }

    def getUsersNotInProject (Long projectId) {
        RestResponse resp = rest.get("${Holders.config.backEnd}/project/getUsersNotInProject?id=${projectId}")
        resp.json.users
    }

    def getComments(Long taskId) {
        RestResponse resp = rest.get("${Holders.config.backEnd}/comment/getComments?taskId=${taskId}")
        def comments = resp.json.comments
        comments.each{ comment ->
            comment.user = getUser(comment.user.id)
            comment.created = simpleDateFormat.parse(comment.created)
        }
        comments
    }

    def getComment(Long id) {
        RestResponse resp = rest.get("${Holders.config.backEnd}/comment/show/${id}")
        def comment = [
                id: resp.json.id,
                content: resp.json.content,
                date:  resp.json.created,
                userId: resp.json.user.id
        ]
        comment
    }

}

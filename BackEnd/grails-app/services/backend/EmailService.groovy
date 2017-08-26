package backend

import com.BackEnd.Email
import com.BackEnd.User

class EmailService {

    def mailService
    def groovyPageRenderer

    def sendEmail (Email email) {

        mailService.sendMail {

            to email.toAddress
            subject email.subject
            text email.content

        }

    }

def invite (Email email, User referrer, String referree) {

    String htmlTemplate = groovyPageRenderer.render([view: "/email/invite", model: [
            referrer: referrer.name,
            name: referree
    ]])

    mailService.sendMail {
        to email.toAddress
        subject email.subject
        html htmlTemplate
    }
}

}

package backend

import com.BackEnd.Email

class EmailService {

    def mailService

    def sendEmail (Email email) {

        mailService.sendMail {

            to email.toAddress
            subject email.subject
            text email.content

        }

    }



}

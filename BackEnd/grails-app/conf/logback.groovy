import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.html.HTMLLayout
import ch.qos.logback.classic.net.SMTPAppender
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.spi.CyclicBufferTracker
import grails.util.BuildSettings
import grails.util.Environment

final String LOGGING_PATERN = "%d |-%-5level - %logger{0} - %msg%n"

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = LOGGING_PATERN
    }
}

String fromEmail = "Cover Dev <cover.dev@ci4.me>"

if (Environment.PRODUCTION == Environment.current) {
    fromEmail = "Cover <cover@ci4.me>"
}

logger 'grails.app.controllers', DEBUG, ['STDOUT'], false
logger 'grails.app.services', DEBUG, ['STDOUT'], false
logger 'grails.app.domain', DEBUG, ['STDOUT'], false
logger 'grails.app.taglib', DEBUG, ['STDOUT'], false

appender("EMAIL", SMTPAppender) {
    smtpHost = "email-smtp.eu-west-1.amazonaws.com"
    smtpPort = 587
    STARTTLS = true
    includeCallerData = true
    asynchronousSending = true
    username = "AKIAJ7G5RDBGMFY5FF6Q"
    password = "AioE3OlhnNGPLiXt824mZ7eVuz0POSlZPXBdNIW8bUT3"
    to = "tech@yallacompare.com"
    from = fromEmail
    subject = "[${Environment.current.toString()}] Son of a glitch"
    layout(HTMLLayout) {
        pattern = "%date %-5level %logger{0} - %message%n"
    }
    cyclicBufferTracker(CyclicBufferTracker) {
        bufferSize = 10
    }
}

if (Environment.isDevelopmentMode()) {
    root(ERROR, ['STDOUT'])
} else {
    root(ERROR, ['STDOUT', 'EMAIL'])
}

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = LOGGING_PATERN
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}

/*
import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')

        pattern =
                '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}
root(ERROR, ['STDOUT'])
*/

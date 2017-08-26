package backend

import com.BackEnd.GeneralInterceptor
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(GeneralInterceptor)
class GeneralInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test general interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"general")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}

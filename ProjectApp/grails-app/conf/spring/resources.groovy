import com.ProjectApp.ProjectAppAuthenticationProvider
import com.ProjectApp.UserPasswordEncoderListener
import java.text.SimpleDateFormat

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))
        rest(grails.plugins.rest.client.RestBuilder)

        simpleDateFormat (java.text.SimpleDateFormat, "yyyy-MM-dd'T'HH:mm:ss'Z'")

        userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))

        projectAppAuthenticationProvider(ProjectAppAuthenticationProvider) {
            rest = ref('rest')
            getObjectsService = ref('getObjectsService')
     }
}

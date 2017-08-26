import java.text.SimpleDateFormat

// Place your Spring DSL code here
beans = {
        rest(grails.plugins.rest.client.RestBuilder)

        simpleDateFormat (java.text.SimpleDateFormat, "yyyy-MM-dd'T'HH:mm:ss'Z'")
}

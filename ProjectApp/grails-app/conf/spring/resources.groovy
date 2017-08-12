// Place your Spring DSL code here
beans = {
        rest(grails.plugins.rest.client.RestBuilder)
        server("localhost")
        port("8090")
        restAddress("http://localhost:8090/project/list")
}

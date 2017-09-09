

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.useBasicAuth = true
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.BackEnd.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.BackEnd.UserRole'
grails.plugin.springsecurity.authority.className = 'com.BackEnd.Role'
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.controllerAnnotations.interceptUrlMap = [
	/*[pattern: '/',               access: ['permitAll']],*/
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/api/login',      access: ['permitAll']],
	[pattern: '/api/logout',     access: ['permitAll']],
	[pattern: '/user/**',		 access: ['permitAll']],
	[pattern: '/project/**',	 access: ['permitAll']],
	[pattern: '/task/**',		 access: ['permitAll']]
]


grails.plugin.springsecurity.filterChain.chainMap = [
		/*[pattern: '/**',				filters: 'JOINED_FILTERS']*/
		[pattern: '/api/**',         filters: 'JOINED_FILTERS,-exceptionTranslationFilter'],
		[pattern: '/**',             filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter']
]
grails.plugin.springsecurity.rest.logout.endpointUrl = '/api/logout'
grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
grails.plugin.springsecurity.rest.token.validation.headerName = 'X-Auth-Token'
grails.plugin.springsecurity.rest.token.storage.memcached.hosts = 'localhost:11211'
grails.plugin.springsecurity.rest.token.storage.useGorm = true
grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = 'com.BackEnd.AuthenticationToken'
grails.plugin.springsecurity.rest.token.storage.memcached.username = ''
grails.plugin.springsecurity.rest.token.storage.memcached.password = ''
grails.plugin.springsecurity.rest.token.storage.memcached.expiration = 86400
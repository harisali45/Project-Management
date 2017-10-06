

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.BackEnd.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.BackEnd.UserRole'
grails.plugin.springsecurity.authority.className = 'com.BackEnd.Role'
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.interceptUrlMap = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],

	//REST API URL's
	[pattern: '/api/login',		 access: ['ROLE_ANONYMOUS']],
	[pattern: '/oauth/access_token', access: ['ROLE_ANONYMOUS']],
	[pattern: '/project/**', access: ['isAuthenticated()'], httpMethod: 'GET'],
	[pattern: '/project/**', access: ['isAuthenticated()'], httpMethod: 'POST'],

	[pattern: '/task/**', access: ['isAuthenticated()'], httpMethod: 'GET'],
	[pattern: '/task/**', access: ['isAuthenticated()'], httpMethod: 'DELETE'],
	[pattern: '/task/**', access: ['isAuthenticated()'], httpMethod: 'PUT'],
	[pattern: '/task/**', access: ['isAuthenticated()'], httpMethod: 'POST'],

	[pattern: '/comment/**', access: ['isAuthenticated()'], httpMethod: 'POST'],
	[pattern: '/comment/**', access: ['isAuthenticated()'], httpMethod: 'GET'],

	[pattern: '/notification/**', access: ['isAuthenticated()'], httpMethod: 'POST'],
	[pattern: '/notification/**', access: ['isAuthenticated()'], httpMethod: 'GET'],

	[pattern: '/user/updateDetails', access: ['permitAll'], httpMethod: 'POST'],
	[pattern: '/user/**', access: ['isAuthenticated()'], httpMethod: 'GET'],
	[pattern: '/user/**', access: ['isAuthenticated()'], httpMethod: 'POST'],

	[pattern: '/access/**', access: ['permitAll'], httpMethod: 'GET'],
	[pattern: '/access/**', access: ['permitAll'], httpMethod: 'POST']
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/api/**',filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'],
	[pattern: '/**', filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter']
	/*[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/** /js/**',       filters: 'none'],
	[pattern: '/** /css/**',      filters: 'none'],
	[pattern: '/** /images/**',   filters: 'none'],
	[pattern: '/** /favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']*/
]


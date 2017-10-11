

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.ProjectApp.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.ProjectApp.UserRole'
grails.plugin.springsecurity.authority.className = 'com.ProjectApp.Role'
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/project/list'
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.ui.password.minLength=1
//grails.plugin.springsecurity.auth.loginFormUrl = '/'
//grails.plugin.springsecurity.failureHandler.defaultFailureUrl = '/'
grails.plugin.springsecurity.useRoleGroups = false
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/sign-in',        access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/logout', 		 access: ['isAuthenticated()']],
	[pattern: '/login/*', 		 access: ['permitAll']],

	[pattern: '/user/showSignUp',access: ['permitAll']],
	[pattern: '/user/signUp',	 access: ['permitAll']],
	[pattern: '/access/*', 		 access: ['permitAll']],

	[pattern: '/project/*', 	access: ['isAuthenticated()']],
	[pattern: '/task/*', 		access: ['isAuthenticated()']],
	[pattern: '/user/*', 		access: ['isAuthenticated()']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.providerNames = [
		'projectAppAuthenticationProvider']/*,
		'anonymousAuthenticationProvider',
		'rememberMeAuthenticationProvider']*/

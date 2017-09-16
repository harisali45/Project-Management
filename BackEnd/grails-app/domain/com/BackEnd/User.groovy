package com.BackEnd

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/*@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)*/
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false

	String name
	String email

	static hasMany = [project: Project, reportedBy: Task, assignedTo: Task, comment: Comment, contributor: Project]
	static mappedBy = [
			reportedBy : 'reportedBy',
			assignedTo : 'assignedTo'
	]

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		//password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
		password
	}

	static transients = ['springSecurityService']

	static constraints = {
		password blank: false, password: true
		username blank: false, unique: true
	}

	static mapping = {
		password column: '`password`'
	}
}

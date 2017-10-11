package com.ProjectApp

import grails.plugins.rest.client.RestResponse
import grails.util.Holders
import org.grails.web.util.WebUtils
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class ProjectAppAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    def rest
    def getObjectsService

    public Authentication authenticate (Authentication authentication)
                                    throws AuthenticationException {
        RestResponse resp = rest.post("${Holders.config.backEnd}api/login") {
            contentType("application/json")
            accept ("application/json")
            json {
                username = authentication.name
                password = authentication.credentials.toString()
            }
        }
        if(resp.status != 200) {
            throw new BadCredentialsException("Invalid username or password")
        } else {
            def session = WebUtils.retrieveGrailsWebRequest().session
            session.putAt("accessToken", resp.json.access_token)
            def user = getObjectsService.getUserByUsername(authentication.name)
            session.putAt("name",user.name)
            session.putAt("username",authentication.name)
            session.putAt("email",user.email)
            session.putAt("userId",user.id)
            session.putAt("notifications", getObjectsService.getNotifications(user.id))
            UserDetails userDetails = retrieveUser(authentication.name, (UsernamePasswordAuthenticationToken) authentication)
            return createSuccessAuthentication(userDetails, authentication, userDetails)
        }
    }

    void additionalAuthenticationChecks(UserDetails userDetails,
                                        UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

    }

    protected UserDetails retrieveUser(String username,
                                                UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        UserDetails userDetails = new UserDetails() {
            @Override
            Collection getAuthorities() {
                return [new GrantedAuthority() {
                    @Override
                    String getAuthority() {
                        "ROLE_USER"
                    }
                }]
            }

            @Override
            String getPassword() {
                return authentication.credentials.toString()
            }

            @Override
            String getUsername() {
                return username
            }

            @Override
            boolean isAccountNonExpired() {
                return true
            }

            @Override
            boolean isAccountNonLocked() {
                return true
            }

            @Override
            boolean isCredentialsNonExpired() {
                return true
            }

            @Override
            boolean isEnabled() {
                return true
            }
        }
        userDetails
    }
}

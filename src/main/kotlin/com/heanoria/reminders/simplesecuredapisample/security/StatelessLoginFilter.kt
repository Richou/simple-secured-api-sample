package com.heanoria.reminders.simplesecuredapisample.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.heanoria.reminders.simplesecuredapisample.dto.Credentials
import com.heanoria.reminders.simplesecuredapisample.dto.User
import com.heanoria.reminders.simplesecuredapisample.dto.UserFull
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import com.heanoria.reminders.simplesecuredapisample.services.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class StatelessLoginFilter(private val urlMapping: String, authenticationManager: AuthenticationManager, private val userService: UserService, private val tokenAuthenticationService: TokenAuthenticationService): AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(urlMapping)) {

    init {
        setAuthenticationManager(authenticationManager)
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val credentials: Credentials = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)
        val loginToken = UsernamePasswordAuthenticationToken(credentials.email, credentials.password)
        return authenticationManager.authenticate(loginToken)
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        if (authResult?.principal is UserFull) {
            val userFull : UserFull = authResult.principal as UserFull
            val loadedUser = this.userService.getEntityByMail(userFull.getEmail())
            val userAuthentication = UserAuthentication(loadedUser)

            this.tokenAuthenticationService.addAuthentication(response, userAuthentication)

            SecurityContextHolder.getContext().authentication = userAuthentication
        }
    }
}
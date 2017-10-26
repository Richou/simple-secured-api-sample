package com.heanoria.reminders.simplesecuredapisample.security

import com.heanoria.reminders.simplesecuredapisample.exceptions.ExpiredTokenException
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class StatelessAuthenticationFilter(private val tokenAuthenticationService: TokenAuthenticationService, authenticationManager: AuthenticationManager) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response : HttpServletResponse, chain: FilterChain) {
        try {
            val authentication: Authentication? = tokenAuthenticationService.getAuthentication(request)
            SecurityContextHolder.getContext().authentication = authentication
            chain.doFilter(request, response)
        } catch (exception: ExpiredTokenException) {
            response.sendError(HttpStatus.FORBIDDEN.value(), exception.message)
        }
    }
}
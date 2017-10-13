package com.heanoria.reminders.simplesecuredapisample.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest


class StatelessAuthenticationFilter(private val tokenAuthenticationService: TokenAuthenticationService) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response : ServletResponse?, chain: FilterChain?) {
        val httpRequest : HttpServletRequest = request as HttpServletRequest
        val authentication: Authentication? = tokenAuthenticationService.getAuthentication(httpRequest)
        SecurityContextHolder.getContext().authentication = authentication
        chain?.doFilter(request, response)
        SecurityContextHolder.getContext().authentication = null
    }
}
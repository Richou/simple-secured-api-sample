package com.heanoria.reminders.simplesecuredapisample.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.heanoria.reminders.simplesecuredapisample.dto.Token
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class TokenAuthenticationService(val tokenHandler: TokenHandler) {

    private val AUTH_HEADER_NAME = "Authorization"

    private val objectMapper: ObjectMapper = ObjectMapper()

    fun addAuthentication(response: HttpServletResponse?, authentication: UserAuthentication) {
        val user = authentication.details
        val tokenForUser = tokenHandler.createTokenForUser(user)
        response?.addHeader(AUTH_HEADER_NAME, tokenForUser)
        val token = Token(tokenForUser)
        response?.writer?.print(objectMapper.writeValueAsString(token))
    }

    fun getAuthentication(request: HttpServletRequest): UserAuthentication? {
        val token = request.getHeader(AUTH_HEADER_NAME)
        if (token != null) {
            val user: UserEntity = tokenHandler.parseUserFromToken(token)
            return UserAuthentication(user)
        }
        return null
    }
}
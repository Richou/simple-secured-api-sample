package com.heanoria.reminders.simplesecuredapisample.security

import com.heanoria.reminders.simplesecuredapisample.configuration.properties.TokenProperties
import com.heanoria.reminders.simplesecuredapisample.exceptions.ExpiredTokenException
import com.heanoria.reminders.simplesecuredapisample.exceptions.InvalidTokenSignatureException
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserRoleEntity
import com.heanoria.reminders.simplesecuredapisample.services.UserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.security.KeyPair
import java.security.SignatureException
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import kotlin.streams.toList


class TokenHandler(private val keyPair: KeyPair, private val userService: UserService, private val tokenProperties: TokenProperties) {

    private val ROLES_CLAIMS_KEY = "roles"
    private val EMAIL_CLAIMS_KEY = "email"
    private val USER_ID_CLAIMS_KEY = "uid"
    private val USERNAME_CLAIMS_KEY = "username"

    fun createTokenForUser(user: UserEntity): String {
        return Jwts.builder().setClaims(buildClaimsMap(user)).setExpiration(calculateExpirationDate()).signWith(SignatureAlgorithm.RS512, keyPair.private).compact()
    }

    fun parseUserFromToken(token:String) : UserEntity {
        val mailFromToken :String = getMailFromToken(token) as String
        return userService.getEntityByMail(mailFromToken)
    }

    private fun getMailFromToken(token: String) = validateTokenSignature(token)[EMAIL_CLAIMS_KEY]

    private fun validateTokenSignature(token : String) : Claims {
        try {
            return Jwts.parser().setSigningKey(keyPair.public)
                    .parseClaimsJws(token).body
        } catch (exception: SignatureException) {
            throw InvalidTokenSignatureException()
        } catch (exception: ExpiredJwtException) {
            throw ExpiredTokenException(exception.message)
        }
    }

    private fun calculateExpirationDate(): Date? {
        val current = LocalDateTime.now()
        return Date.from(current.plusMinutes(tokenProperties.validityInMinutes).atZone(ZoneId.systemDefault()).toInstant())
    }

    private fun buildClaimsMap(user: UserEntity) : Map<String, Any?> {
        val roles = user.authorities?.stream()?.map { userRoleEntity: UserRoleEntity -> userRoleEntity.role?.authority }?.toList()
        return mapOf(ROLES_CLAIMS_KEY to roles, USER_ID_CLAIMS_KEY to user.id, EMAIL_CLAIMS_KEY to user.email, USERNAME_CLAIMS_KEY to user.username)
    }

}
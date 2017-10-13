package com.heanoria.reminders.simplesecuredapisample.security

import com.heanoria.reminders.simplesecuredapisample.dto.GrantedRole
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserRoleEntity
import org.springframework.security.core.Authentication


class UserAuthentication(val user: UserEntity) : Authentication{

    private var authenticated: Boolean = true

    override fun getAuthorities(): List<GrantedRole> = user.authorities.map { userRoleEntity: UserRoleEntity -> GrantedRole(userRoleEntity) }.toList()

    override fun setAuthenticated(authenticated: Boolean) {
        this.authenticated = authenticated
    }

    override fun getName() = user.username

    override fun getCredentials() = user.password

    override fun getPrincipal() = user.email

    override fun isAuthenticated() = this.authenticated

    override fun getDetails() = user
}
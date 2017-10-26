package com.heanoria.reminders.simplesecuredapisample.datas.dto

import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserRoleEntity
import org.springframework.security.core.userdetails.UserDetails
import kotlin.streams.toList

data class UserFull(private val userEntity: UserEntity?) : UserDetails {

    fun getEmail() = userEntity?.email

    override fun getAuthorities() = userEntity?.authorities?.stream()?.map { userRoleEntity: UserRoleEntity -> GrantedRole(userRoleEntity) }?.toList()

    override fun isEnabled() = true

    override fun getUsername() = userEntity?.username

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = userEntity?.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}
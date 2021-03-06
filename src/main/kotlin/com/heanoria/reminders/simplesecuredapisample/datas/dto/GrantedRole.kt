package com.heanoria.reminders.simplesecuredapisample.datas.dto

import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserRoleEntity
import org.springframework.security.core.GrantedAuthority


data class GrantedRole(private val userRoleEntity: UserRoleEntity) : GrantedAuthority {
    override fun getAuthority() = userRoleEntity.role?.authority
}
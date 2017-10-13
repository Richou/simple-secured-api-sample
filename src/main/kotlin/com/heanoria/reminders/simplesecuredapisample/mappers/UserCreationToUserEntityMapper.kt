package com.heanoria.reminders.simplesecuredapisample.mappers

import com.heanoria.reminders.simplesecuredapisample.dto.UserCreate
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.RoleEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserRoleEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


class UserCreationToUserEntityMapper(private val roleEntity: RoleEntity) {

    fun map(userCreate: UserCreate): UserEntity {
        val encoder = BCryptPasswordEncoder()
        val userEntity = UserEntity()
        userEntity.username = userCreate.username
        userEntity.email = userCreate.email
        userEntity.password = encoder.encode(userCreate.password)
        val userRoleEntity = UserRoleEntity()
        userRoleEntity.user = userEntity
        userRoleEntity.role = roleEntity
        userEntity.authorities = listOf(userRoleEntity)
        return userEntity
    }

}
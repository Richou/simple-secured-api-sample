package com.heanoria.reminders.simplesecuredapisample.mappers

import com.heanoria.reminders.simplesecuredapisample.dto.Role
import com.heanoria.reminders.simplesecuredapisample.dto.User
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserRoleEntity
import kotlin.streams.toList


class UserEntityToUserMapper() {

    fun map(userEntity: UserEntity) = User(userEntity.id, userEntity.username, userEntity.email, userEntity.authorities?.stream()?.map { userRole: UserRoleEntity -> Role(userRole.id, userRole.role.authority) }?.toList())
}
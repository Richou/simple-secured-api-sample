package com.heanoria.reminders.simplesecuredapisample.datas.mappers

import com.heanoria.reminders.simplesecuredapisample.datas.dto.Role
import com.heanoria.reminders.simplesecuredapisample.datas.dto.User
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserRoleEntity
import kotlin.streams.toList


class UserEntityToUserMapper {

    fun apply(userEntity: UserEntity?) = User(userEntity?.id, userEntity?.username, userEntity?.email, userEntity?.authorities?.stream()?.map { userRole: UserRoleEntity -> Role(userRole.id, userRole.role?.authority) }?.toList())
}
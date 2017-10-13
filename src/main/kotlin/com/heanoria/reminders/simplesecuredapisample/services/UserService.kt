package com.heanoria.reminders.simplesecuredapisample.services

import com.google.common.base.Preconditions
import com.heanoria.reminders.simplesecuredapisample.dto.Role
import com.heanoria.reminders.simplesecuredapisample.dto.User
import com.heanoria.reminders.simplesecuredapisample.dto.UserCreate
import com.heanoria.reminders.simplesecuredapisample.dto.UserFull
import com.heanoria.reminders.simplesecuredapisample.exceptions.NotFoundException
import com.heanoria.reminders.simplesecuredapisample.mappers.UserCreationToUserEntityMapper
import com.heanoria.reminders.simplesecuredapisample.mappers.UserEntityToUserMapper
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserRoleEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.RoleRepository
import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import kotlin.streams.toList

class UserService (private val userRepository: UserRepository, private val roleRepository: RoleRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String) = UserFull(userRepository.findByEmail(email)) ?: throw NotFoundException()

    fun getEntityByMail(email: String?) = userRepository.findByEmail(email) ?: throw NotFoundException()

    fun getByEmail(username: String): User {
        val userEntity = userRepository.findByEmail(username) ?: throw NotFoundException()
        return UserEntityToUserMapper().map(userEntity)
    }

    fun createUser(userCreate: UserCreate): User {
        Preconditions.checkNotNull(userCreate)
        Preconditions.checkNotNull(userCreate.email)
        Preconditions.checkNotNull(userCreate.password)
        Preconditions.checkNotNull(userCreate.username)
        val mapper = UserCreationToUserEntityMapper(this.roleRepository.findByAuthority("ROLE_USER"))
        return UserEntityToUserMapper().map(this.userRepository.save(mapper.map(userCreate)))
    }
}
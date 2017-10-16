package com.heanoria.reminders.simplesecuredapisample.services.impl

import com.google.common.base.Preconditions
import com.heanoria.reminders.simplesecuredapisample.dto.User
import com.heanoria.reminders.simplesecuredapisample.dto.UserCreate
import com.heanoria.reminders.simplesecuredapisample.dto.UserFull
import com.heanoria.reminders.simplesecuredapisample.dto.UserUpdate
import com.heanoria.reminders.simplesecuredapisample.exceptions.NotFoundException
import com.heanoria.reminders.simplesecuredapisample.mappers.UserCreationToUserEntityMapper
import com.heanoria.reminders.simplesecuredapisample.mappers.UserEntityToUserMapper
import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.RoleRepository
import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.UserRepository
import com.heanoria.reminders.simplesecuredapisample.services.UserService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.transaction.annotation.Transactional

open class UserServiceImpl (private val userRepository: UserRepository, private val roleRepository: RoleRepository) : UserService, UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(email: String) = UserFull(userRepository.findByEmail(email)) ?: throw NotFoundException()

    override fun getEntityByMail(email: String?) = userRepository.findByEmail(email) ?: throw NotFoundException()

    override fun getByEmail(username: String): User {
        val userEntity = userRepository.findByEmail(username) ?: throw NotFoundException()
        return UserEntityToUserMapper().map(userEntity)
    }

    override fun createUser(userCreate: UserCreate): User {
        Preconditions.checkNotNull(userCreate)
        Preconditions.checkNotNull(userCreate.email)
        Preconditions.checkNotNull(userCreate.password)
        Preconditions.checkNotNull(userCreate.username)
        val mapper = UserCreationToUserEntityMapper(this.roleRepository.findByAuthority("ROLE_USER"))
        return UserEntityToUserMapper().map(this.userRepository.save(mapper.map(userCreate)))
    }

    @Transactional(readOnly = false)
    override fun deleteUser(email: String) {
        userRepository.deleteByEmail(email)
    }

    @Transactional(readOnly = false)
    override fun editUserByEmail(email: String, userUpdate: UserUpdate): User {
        val userFromDb = userRepository.findByEmail(email) ?: throw NotFoundException()
        userFromDb.username = userUpdate.username
        return UserEntityToUserMapper().map(userRepository.save(userFromDb))
    }
}
package com.heanoria.reminders.simplesecuredapisample.services.impl

import com.google.common.base.Preconditions
import com.heanoria.reminders.simplesecuredapisample.datas.dto.User
import com.heanoria.reminders.simplesecuredapisample.datas.dto.UserCreate
import com.heanoria.reminders.simplesecuredapisample.datas.dto.UserFull
import com.heanoria.reminders.simplesecuredapisample.datas.dto.UserUpdate
import com.heanoria.reminders.simplesecuredapisample.exceptions.NotFoundException
import com.heanoria.reminders.simplesecuredapisample.datas.mappers.UserCreationToUserEntityMapper
import com.heanoria.reminders.simplesecuredapisample.datas.mappers.UserEntityToUserMapper
import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.RoleRepository
import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.UserRepository
import com.heanoria.reminders.simplesecuredapisample.services.UserService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.transaction.annotation.Transactional

open class UserServiceImpl (private val userRepository: UserRepository, private val roleRepository: RoleRepository) : UserService, UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(email: String) : UserFull {
        val userEntity = userRepository.findByEmail(email) ?: throw UsernameNotFoundException("User not found.")
        return UserFull(userEntity)
    }

    override fun getEntityByMail(email: String?) = userRepository.findByEmail(email) ?: throw NotFoundException("User not found.")

    override fun getByEmail(username: String): User {
        val userEntity = userRepository.findByEmail(username) ?: throw NotFoundException("User not found.")
        return UserEntityToUserMapper().apply(userEntity)
    }

    override fun createUser(userCreate: UserCreate): User {
        Preconditions.checkNotNull(userCreate)
        Preconditions.checkNotNull(userCreate.email)
        Preconditions.checkNotNull(userCreate.password)
        Preconditions.checkNotNull(userCreate.username)
        val mapper = UserCreationToUserEntityMapper(this.roleRepository.findByAuthority("ROLE_USER"))
        return UserEntityToUserMapper().apply(this.userRepository.save(mapper.apply(userCreate)))
    }

    @Transactional(readOnly = false)
    override fun deleteUser(email: String) {
        userRepository.deleteByEmail(email)
    }

    @Transactional(readOnly = false)
    override fun editUserByEmail(email: String, userUpdate: UserUpdate): User {
        val userFromDb = userRepository.findByEmail(email) ?: throw NotFoundException("User not found.")
        userFromDb.username = userUpdate.username
        return UserEntityToUserMapper().apply(userRepository.save(userFromDb))
    }
}
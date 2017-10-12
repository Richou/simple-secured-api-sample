package com.heanoria.reminders.simplesecuredapisample.services

import com.heanoria.reminders.simplesecuredapisample.dto.Role
import com.heanoria.reminders.simplesecuredapisample.dto.User
import com.heanoria.reminders.simplesecuredapisample.exceptions.NotFoundException
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserRoleEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import kotlin.streams.toList

class UserService (private var userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String?) = userRepository.findByEmail(email)

    fun getByEmail(username: String?): User {
        val userEntity = userRepository.findByEmail(username) ?: throw NotFoundException()
        return User(userEntity.id, userEntity.username, userEntity.email, userEntity.authorities.stream().map { userRole: UserRoleEntity? -> Role(userRole?.id, userRole?.authority) }.toList())
    }

}
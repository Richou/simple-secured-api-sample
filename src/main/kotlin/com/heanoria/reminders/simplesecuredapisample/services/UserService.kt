package com.heanoria.reminders.simplesecuredapisample.services

import com.heanoria.reminders.simplesecuredapisample.datas.dto.User
import com.heanoria.reminders.simplesecuredapisample.datas.dto.UserCreate
import com.heanoria.reminders.simplesecuredapisample.datas.dto.UserUpdate
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.transaction.annotation.Transactional

interface UserService : UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(email: String): UserDetails

    fun getEntityByMail(email: String?) : UserEntity

    fun getByEmail(username: String): User

    fun createUser(userCreate: UserCreate): User

    @Transactional(readOnly = false)
    fun deleteUser(email: String)

    fun editUserByEmail(email: String, userUpdate: UserUpdate): User
}
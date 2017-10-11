package com.heanoria.reminders.simplesecuredapisample.persistence.repositories

import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String?): UserEntity
}
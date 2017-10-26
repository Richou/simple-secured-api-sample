package com.heanoria.reminders.simplesecuredapisample.persistence.repositories

import com.heanoria.reminders.simplesecuredapisample.persistence.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String?): UserEntity?

    @Modifying
    @Query("DELETE FROM UserEntity u WHERE u.email = :email")
    fun deleteByEmail(@Param("email") email: String)
}
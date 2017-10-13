package com.heanoria.reminders.simplesecuredapisample.persistence.repositories

import com.heanoria.reminders.simplesecuredapisample.persistence.entities.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.util.*


interface RoleRepository : JpaRepository<RoleEntity, UUID> {
    fun findByAuthority(@Param("authority") authority: String) : RoleEntity
}
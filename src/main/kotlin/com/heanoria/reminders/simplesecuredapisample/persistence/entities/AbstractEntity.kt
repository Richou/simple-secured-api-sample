package com.heanoria.reminders.simplesecuredapisample.persistence.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractEntity (
        @CreatedDate @Basic @Column(name = "date_creation")
        var dateCreation: Date? = null,

        @LastModifiedDate @Basic @Column(name = "date_updated")
        var dateUpdated: Date? = null
)
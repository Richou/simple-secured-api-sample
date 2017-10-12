package com.heanoria.reminders.simplesecuredapisample.persistence.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles")
class RoleEntity {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
    val id: UUID? = null

    @Basic
    @Column(name = "authority")
    val authority: String = ""
}
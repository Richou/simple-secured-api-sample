package com.heanoria.reminders.simplesecuredapisample.persistence.entities

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles")
class RoleEntity(
    @Id @Column(name = "id") @GenericGenerator(name = "uuid-gen", strategy = "uuid2") @GeneratedValue(generator = "uuid-gen") @Type(type = "pg-uuid")
    var id: UUID? = null,

    @Basic @Column(name = "authority")
    var authority: String? = null)
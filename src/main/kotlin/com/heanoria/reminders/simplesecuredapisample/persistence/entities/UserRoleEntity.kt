package com.heanoria.reminders.simplesecuredapisample.persistence.entities

import org.hibernate.annotations.GenericGenerator
import org.springframework.security.core.GrantedAuthority
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_role")
class UserRoleEntity {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
    lateinit var id: UUID

    @ManyToOne
    @JoinColumn(name = "id_user")
    lateinit var user: UserEntity

    @ManyToOne
    @JoinColumn(name = "id_role")
    lateinit var role: RoleEntity
}
package com.heanoria.reminders.simplesecuredapisample.persistence.entities

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.springframework.security.core.GrantedAuthority
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_role")
class UserRoleEntity(
    @Id @Column(name = "id") @GenericGenerator(name = "uuid-gen", strategy = "uuid2") @GeneratedValue(generator = "uuid-gen") @Type(type = "pg-uuid")
    var id: UUID? = null,

    @ManyToOne @JoinColumn(name = "id_user")
    var user: UserEntity? = null,

    @ManyToOne @JoinColumn(name = "id_role")
    var role: RoleEntity? = null)
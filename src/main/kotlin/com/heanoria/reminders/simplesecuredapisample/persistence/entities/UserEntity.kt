package com.heanoria.reminders.simplesecuredapisample.persistence.entities

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id @Column(name = "id") @GenericGenerator(name = "uuid-gen", strategy = "uuid2") @GeneratedValue(generator = "uuid-gen") @Type(type = "pg-uuid")
    var id: UUID? = null,

    @Basic @Column(name = "username")
    var username: String? = null,

    @Basic @Column(name = "password")
    var password: String? = null,

    @Basic @Column(name = "email")
    var email: String? = null,

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
    var authorities: List<UserRoleEntity>? = emptyList())
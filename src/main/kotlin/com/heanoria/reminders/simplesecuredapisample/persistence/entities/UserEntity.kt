package com.heanoria.reminders.simplesecuredapisample.persistence.entities

import org.hibernate.annotations.GenericGenerator
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
    lateinit var id: UUID

    @Basic
    @Column(name = "username")
    lateinit var username: String

    @Basic
    @Column(name = "password")
    lateinit var password: String

    @Basic
    @Column(name = "email")
    lateinit var email: String

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
    var authorities:List<UserRoleEntity> = emptyList()
}
package com.heanoria.reminders.simplesecuredapisample.persistence.entities

import org.hibernate.annotations.GenericGenerator
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity : UserDetails {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
    val id: UUID? = null

    @Basic
    @Column(name = "username")
    private val username: String = ""

    @Basic
    @Column(name = "password")
    private val password: String = ""

    @Basic
    @Column(name = "email")
    val email: String = ""

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
    private val authorities:List<UserRoleEntity> = emptyList()

    override fun getUsername() = username

    override fun getPassword() = password

    override fun getAuthorities() = authorities

    override fun isEnabled() = true

    override fun isCredentialsNonExpired() = true

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}
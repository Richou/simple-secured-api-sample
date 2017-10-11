package com.heanoria.reminders.simplesecuredapisample.persistence.entities

import org.hibernate.annotations.GenericGenerator
import org.springframework.security.core.GrantedAuthority
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_role")
class UserRoleEntity : GrantedAuthority {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
    val id: UUID? = null

    @ManyToOne
    @JoinColumn(name = "id_user")
    val user: UserEntity? = null

    @ManyToOne
    @JoinColumn(name = "id_role")
    private val role: RoleEntity? = null

    override fun getAuthority(): String? {
        return role?.authority
    }
}
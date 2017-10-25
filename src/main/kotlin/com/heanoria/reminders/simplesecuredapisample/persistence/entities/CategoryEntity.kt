package com.heanoria.reminders.simplesecuredapisample.persistence.entities

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "categories")
class CategoryEntity(
    @Id @Column(name = "id") @GenericGenerator(name = "uuid-gen", strategy = "uuid2") @GeneratedValue(generator = "uuid-gen") @Type(type = "pg-uuid")
    var id: UUID? = null,

    @Basic @Column(name= "key")
    var key: String? = null
)
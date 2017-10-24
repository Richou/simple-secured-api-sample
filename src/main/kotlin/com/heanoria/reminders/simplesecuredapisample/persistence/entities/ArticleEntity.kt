package com.heanoria.reminders.simplesecuredapisample.persistence.entities

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "articles")
class ArticleEntity(
    @Id @Column(name = "id") @GenericGenerator(name = "uuid-gen", strategy = "uuid2") @GeneratedValue(generator = "uuid-gen") @Type(type = "pg-uuid")
    var id: UUID? = null,

    @OneToOne(cascade = arrayOf(CascadeType.ALL)) @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    @Basic @Column(name = "title")
    var title: String? = null,

    @Basic @Column(name = "content")
    var content: String? = null
) : AbstractEntity()
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

    @OneToOne @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    @Basic @Column(name = "title")
    var title: String? = null,

    @Basic @Column(name = "content")
    var content: String? = null,

    @ManyToMany(cascade = arrayOf(CascadeType.MERGE))
    @JoinTable(name = "articles_categories", joinColumns = arrayOf(JoinColumn(name = "article_id", referencedColumnName = "id")), inverseJoinColumns = arrayOf(JoinColumn(name = "categorie_id", referencedColumnName = "id")))
    var categories: List<CategoryEntity> = emptyList()

) : AbstractEntity()
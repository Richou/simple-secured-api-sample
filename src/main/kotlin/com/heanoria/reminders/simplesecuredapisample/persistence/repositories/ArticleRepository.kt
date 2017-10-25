package com.heanoria.reminders.simplesecuredapisample.persistence.repositories

import com.heanoria.reminders.simplesecuredapisample.persistence.entities.ArticleEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.util.*


interface ArticleRepository : JpaRepository<ArticleEntity, UUID> {
    fun findByUserId(@Param("pageable") pageable: Pageable, @Param("userId") userId: UUID) : List<ArticleEntity>

    fun getByIdAndUserId(@Param("articleId") articleId: UUID, @Param("userId") userId: UUID?) : ArticleEntity
}
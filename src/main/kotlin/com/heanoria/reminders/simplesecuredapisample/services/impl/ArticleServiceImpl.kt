package com.heanoria.reminders.simplesecuredapisample.services.impl

import com.heanoria.reminders.simplesecuredapisample.dto.Article
import com.heanoria.reminders.simplesecuredapisample.dto.ArticleCreate
import com.heanoria.reminders.simplesecuredapisample.dto.ArticleUpdate
import com.heanoria.reminders.simplesecuredapisample.exceptions.NotFoundException
import com.heanoria.reminders.simplesecuredapisample.mappers.ArticleEntityToArticleMapper
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.ArticleEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.ArticleRepository
import com.heanoria.reminders.simplesecuredapisample.security.TokenHandler
import com.heanoria.reminders.simplesecuredapisample.security.UserAuthentication
import com.heanoria.reminders.simplesecuredapisample.services.ArticleService
import org.springframework.data.domain.Pageable
import org.springframework.security.core.Authentication
import java.util.*


class ArticleServiceImpl(private val articleRepository: ArticleRepository, private val tokenHandler: TokenHandler) : ArticleService {

    override fun createArticle(articleCreate: ArticleCreate, token: String) : Article? {
        val articleEntity = ArticleEntity()
        articleEntity.title = articleCreate.title
        articleEntity.content = articleCreate.content
        articleEntity.user = tokenHandler.parseUserFromToken(token)
        val savedArticle = articleRepository.save(articleEntity)
        return ArticleEntityToArticleMapper().map(savedArticle)
    }

    override fun updateArticle(articleUpdate: ArticleUpdate): Article? {
        val articleFromDb: ArticleEntity = this.articleRepository.findOne(articleUpdate.id) ?: throw NotFoundException()
        articleFromDb.title = articleUpdate.title
        articleFromDb.content = articleUpdate.content
        articleFromDb.dateUpdated = null
        val updatedArticle = articleRepository.save(articleFromDb)
        return ArticleEntityToArticleMapper().map(updatedArticle)
    }

    override fun getArticlesForUser(pageable: Pageable, id: UUID): List<Article> {
        return this.articleRepository.findByUserId(pageable, id).map { articleEntity -> ArticleEntityToArticleMapper().map(articleEntity) }.toList()
    }

    override fun isUserOwnerOfArticle(principal: Authentication, articleUpdate: ArticleUpdate): Boolean {
        val findByIdAndUserId = this.articleRepository.getByIdAndUserId(articleUpdate.id, (principal as UserAuthentication).user.id)
        return findByIdAndUserId != null
    }
}
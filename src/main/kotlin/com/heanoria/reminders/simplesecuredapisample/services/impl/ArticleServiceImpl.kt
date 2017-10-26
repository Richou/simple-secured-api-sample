package com.heanoria.reminders.simplesecuredapisample.services.impl

import com.heanoria.reminders.simplesecuredapisample.datas.dto.Article
import com.heanoria.reminders.simplesecuredapisample.datas.dto.ArticleCreate
import com.heanoria.reminders.simplesecuredapisample.datas.dto.ArticleUpdate
import com.heanoria.reminders.simplesecuredapisample.exceptions.NotFoundException
import com.heanoria.reminders.simplesecuredapisample.datas.mappers.ArticleEntityToArticleMapper
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.ArticleEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.CategoryEntity
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
        articleEntity.categories = articleCreate.categories.map { category -> CategoryEntity(category.id, category.key) }.toList()
        val savedArticle = articleRepository.save(articleEntity)
        return ArticleEntityToArticleMapper().apply(savedArticle)
    }

    override fun updateArticle(articleUpdate: ArticleUpdate): Article? {
        val articleFromDb: ArticleEntity = this.articleRepository.findOne(articleUpdate.id) ?: throw NotFoundException("Article not found.")
        articleFromDb.title = articleUpdate.title
        articleFromDb.content = articleUpdate.content
        articleFromDb.dateUpdated = null
        articleFromDb.categories = articleUpdate.categories.map { category -> CategoryEntity(category.id, category.key) }
        val updatedArticle = articleRepository.save(articleFromDb)
        return ArticleEntityToArticleMapper().apply(updatedArticle)
    }

    override fun deleteArticle(id: UUID) {
        this.articleRepository.delete(id)
    }

    override fun getArticlesForUser(pageable: Pageable, id: UUID): List<Article> {
        return this.articleRepository.findByUserId(pageable, id).map { articleEntity -> ArticleEntityToArticleMapper().apply(articleEntity) }.toList()
    }

    override fun isUserOwnerOfArticle(principal: Authentication, id: UUID): Boolean {
        return this.articleRepository.getByIdAndUserId(id, (principal as UserAuthentication).user.id) != null
    }
}
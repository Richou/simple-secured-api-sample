package com.heanoria.reminders.simplesecuredapisample.services.impl

import com.heanoria.reminders.simplesecuredapisample.dto.Article
import com.heanoria.reminders.simplesecuredapisample.dto.ArticleCreate
import com.heanoria.reminders.simplesecuredapisample.dto.ArticleUpdate
import com.heanoria.reminders.simplesecuredapisample.exceptions.NotFoundException
import com.heanoria.reminders.simplesecuredapisample.mappers.UserEntityToUserMapper
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.ArticleEntity
import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.ArticleRepository
import com.heanoria.reminders.simplesecuredapisample.security.TokenHandler
import com.heanoria.reminders.simplesecuredapisample.services.ArticleService


class ArticleServiceImpl(private val articleRepository: ArticleRepository, private val tokenHandler: TokenHandler) : ArticleService {

    override fun createArticle(articleCreate: ArticleCreate, token: String) : Article? {
        val articleEntity = ArticleEntity()
        articleEntity.title = articleCreate.title
        articleEntity.content = articleCreate.content
        articleEntity.user = tokenHandler.parseUserFromToken(token)
        val savedArticle = articleRepository.save(articleEntity)
        return Article(savedArticle.id, savedArticle.title, savedArticle.content, UserEntityToUserMapper().map(savedArticle.user))
    }

    override fun updateArticle(articleUpdate: ArticleUpdate): Article? {
        val articleFromDb: ArticleEntity = this.articleRepository.getOne(articleUpdate.id) ?: throw NotFoundException()
        val newArticle = ArticleEntity(articleFromDb.id,  articleFromDb.user, articleUpdate.title, articleUpdate.content)
        val updatedArticle = articleRepository.save(newArticle)
        return Article(updatedArticle.id, updatedArticle.title, updatedArticle.content, UserEntityToUserMapper().map(updatedArticle.user))
    }

}
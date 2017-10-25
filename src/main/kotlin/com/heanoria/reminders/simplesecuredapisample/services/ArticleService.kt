package com.heanoria.reminders.simplesecuredapisample.services

import com.heanoria.reminders.simplesecuredapisample.dto.Article
import com.heanoria.reminders.simplesecuredapisample.dto.ArticleCreate
import com.heanoria.reminders.simplesecuredapisample.dto.ArticleUpdate
import org.springframework.data.domain.Pageable
import org.springframework.security.core.Authentication
import java.util.*


interface ArticleService {
    fun createArticle(articleCreate: ArticleCreate, token: String) : Article?

    fun updateArticle(articleUpdate: ArticleUpdate): Article?

    fun getArticlesForUser(pageable: Pageable, id: UUID): List<Article>

    fun isUserOwnerOfArticle(principal: Authentication, articleUpdate: ArticleUpdate): Boolean
}
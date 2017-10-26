package com.heanoria.reminders.simplesecuredapisample.services

import com.heanoria.reminders.simplesecuredapisample.datas.dto.Article
import com.heanoria.reminders.simplesecuredapisample.datas.dto.ArticleCreate
import com.heanoria.reminders.simplesecuredapisample.datas.dto.ArticleUpdate
import org.springframework.data.domain.Pageable
import org.springframework.security.core.Authentication
import java.util.*


interface ArticleService {
    fun createArticle(articleCreate: ArticleCreate, token: String) : Article?

    fun updateArticle(articleUpdate: ArticleUpdate): Article?

    fun getArticlesForUser(pageable: Pageable, id: UUID): List<Article>

    fun isUserOwnerOfArticle(principal: Authentication, id: UUID): Boolean

    fun deleteArticle(id: UUID)
}
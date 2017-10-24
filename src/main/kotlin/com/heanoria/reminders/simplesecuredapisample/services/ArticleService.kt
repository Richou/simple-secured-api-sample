package com.heanoria.reminders.simplesecuredapisample.services

import com.heanoria.reminders.simplesecuredapisample.dto.Article
import com.heanoria.reminders.simplesecuredapisample.dto.ArticleCreate
import com.heanoria.reminders.simplesecuredapisample.dto.ArticleUpdate


interface ArticleService {
    fun createArticle(articleCreate: ArticleCreate, token: String) : Article?

    fun updateArticle(articleUpdate: ArticleUpdate): Article?
}
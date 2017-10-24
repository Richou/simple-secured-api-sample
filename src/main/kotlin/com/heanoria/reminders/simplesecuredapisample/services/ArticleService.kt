package com.heanoria.reminders.simplesecuredapisample.services

import com.heanoria.reminders.simplesecuredapisample.dto.Article
import com.heanoria.reminders.simplesecuredapisample.dto.ArticleCreate


interface ArticleService {
    fun createArticle(articleCreate: ArticleCreate, token: String) : Article?
}
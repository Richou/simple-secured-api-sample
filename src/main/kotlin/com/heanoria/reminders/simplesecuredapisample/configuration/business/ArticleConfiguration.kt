package com.heanoria.reminders.simplesecuredapisample.configuration.business

import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.ArticleRepository
import com.heanoria.reminders.simplesecuredapisample.security.TokenHandler
import com.heanoria.reminders.simplesecuredapisample.services.impl.ArticleServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ArticleConfiguration {

    @Bean
    fun articleService(articleRepository: ArticleRepository, tokenHandler: TokenHandler) = ArticleServiceImpl(articleRepository, tokenHandler)
}
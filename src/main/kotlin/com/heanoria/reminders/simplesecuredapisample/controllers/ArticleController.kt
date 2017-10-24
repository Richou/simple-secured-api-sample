package com.heanoria.reminders.simplesecuredapisample.controllers

import com.heanoria.reminders.simplesecuredapisample.dto.ArticleCreate
import com.heanoria.reminders.simplesecuredapisample.services.ArticleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class ArticleController(private val articleService: ArticleService) {

    @PostMapping("/articles")
    fun doPostArticle(@RequestBody articleCreate: ArticleCreate, @RequestHeader("Authorization") authorization: String) = this.articleService.createArticle(articleCreate, authorization)
}
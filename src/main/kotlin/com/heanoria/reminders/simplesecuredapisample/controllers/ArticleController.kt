package com.heanoria.reminders.simplesecuredapisample.controllers

import com.heanoria.reminders.simplesecuredapisample.dto.ArticleCreate
import com.heanoria.reminders.simplesecuredapisample.dto.ArticleUpdate
import com.heanoria.reminders.simplesecuredapisample.services.ArticleService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class ArticleController(private val articleService: ArticleService) {

    @PostMapping("/articles")
    fun doPostArticle(@RequestBody articleCreate: ArticleCreate, @RequestHeader("Authorization") authorization: String) = this.articleService.createArticle(articleCreate, authorization)

    @PutMapping("/articles")
    @PreAuthorize("@articleService.isUserOwnerOfArticle(authentication, #articleUpdate)")
    fun doPutArticle(@RequestBody articleUpdate: ArticleUpdate, @RequestHeader("Authorization") authorization: String) = this.articleService.updateArticle(articleUpdate)
}
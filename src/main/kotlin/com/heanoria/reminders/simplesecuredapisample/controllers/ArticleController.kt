package com.heanoria.reminders.simplesecuredapisample.controllers

import com.heanoria.reminders.simplesecuredapisample.datas.dto.ArticleCreate
import com.heanoria.reminders.simplesecuredapisample.datas.dto.ArticleUpdate
import com.heanoria.reminders.simplesecuredapisample.services.ArticleService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1")
class ArticleController(private val articleService: ArticleService) {

    @PostMapping("/articles")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    fun doPostArticle(@RequestBody articleCreate: ArticleCreate, @RequestHeader("Authorization") authorization: String) = this.articleService.createArticle(articleCreate, authorization)

    @PutMapping("/articles")
    @PreAuthorize("@articleService.isUserOwnerOfArticle(authentication, #articleUpdate.id)")
    fun doPutArticle(@RequestBody articleUpdate: ArticleUpdate, @RequestHeader("Authorization") authorization: String) = this.articleService.updateArticle(articleUpdate)

    @DeleteMapping("/articles/{id}")
    @PreAuthorize("@articleService.isUserOwnerOfArticle(authentication, #id)")
    fun doDeleteArticle(@PathVariable id: UUID, @RequestHeader("Authorization") authorization: String) = this.articleService.deleteArticle(id)
}
package com.heanoria.reminders.simplesecuredapisample.mappers

import com.heanoria.reminders.simplesecuredapisample.dto.Article
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.ArticleEntity


class ArticleEntityToArticleMapper {
    fun map(articleEntity: ArticleEntity): Article {
        return Article(articleEntity.id, articleEntity.title, articleEntity.content,
                UserEntityToUserMapper().map(articleEntity.user),
                articleEntity.categories.map { categoryEntity ->  CategoryEntityToCategoryMapper().map(categoryEntity) }.toList(),
                articleEntity.dateCreation, articleEntity.dateUpdated)
    }
}
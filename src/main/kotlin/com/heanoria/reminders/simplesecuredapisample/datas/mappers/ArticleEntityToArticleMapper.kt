package com.heanoria.reminders.simplesecuredapisample.datas.mappers

import com.heanoria.reminders.simplesecuredapisample.datas.dto.Article
import com.heanoria.reminders.simplesecuredapisample.persistence.entities.ArticleEntity


class ArticleEntityToArticleMapper {
    fun apply(articleEntity: ArticleEntity): Article {
        return Article(articleEntity.id, articleEntity.title, articleEntity.content,
                UserEntityToUserMapper().apply(articleEntity.user),
                articleEntity.categories.map { categoryEntity ->  CategoryEntityToCategoryMapper().apply(categoryEntity) }.toList(),
                articleEntity.dateCreation, articleEntity.dateUpdated)
    }
}
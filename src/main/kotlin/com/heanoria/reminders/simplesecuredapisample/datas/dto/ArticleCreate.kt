package com.heanoria.reminders.simplesecuredapisample.datas.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class ArticleCreate(@JsonProperty("title") val title: String, @JsonProperty("content") val content: String, @JsonProperty("categories") val categories: List<Category>)
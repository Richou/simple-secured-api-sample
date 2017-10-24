package com.heanoria.reminders.simplesecuredapisample.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class ArticleCreate(@JsonProperty("title") val title: String, @JsonProperty("content") val content: String)
package com.heanoria.reminders.simplesecuredapisample.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


class ArticleUpdate(@JsonProperty("id") val id: UUID, @JsonProperty("title") val title: String, @JsonProperty("content") val content: String)
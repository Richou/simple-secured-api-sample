package com.heanoria.reminders.simplesecuredapisample.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*


data class Category(
    @JsonProperty("id") val id: UUID?,
    @JsonProperty("key") val key: String?
)
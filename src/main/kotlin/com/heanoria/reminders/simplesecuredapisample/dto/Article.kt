package com.heanoria.reminders.simplesecuredapisample.dto

import java.util.*


data class Article(
    val id: UUID?,
    val title:String?,
    val content:String?,
    val user:User?,
    val dateCreation: Date?,
    val dateUpdated: Date?)
package com.heanoria.reminders.simplesecuredapisample.datas.dto

import java.util.*


data class Article(
    val id: UUID?,
    val title:String?,
    val content:String?,
    val user:User?,
    val categories: List<Category>,
    val dateCreation: Date?,
    val dateUpdated: Date?)
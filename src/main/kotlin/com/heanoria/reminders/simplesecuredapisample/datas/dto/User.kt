package com.heanoria.reminders.simplesecuredapisample.datas.dto

import java.util.*


data class User(val id:UUID?, val username: String?, val email: String?, val authoritie: List<Role>? = emptyList())
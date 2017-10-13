package com.heanoria.reminders.simplesecuredapisample.dto

import java.util.*


class User(val id:UUID?, val username: String?, val email: String?, val authoritie: List<Role>? = emptyList())
package com.heanoria.reminders.simplesecuredapisample.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserUpdate (@JsonProperty("username") val username: String)
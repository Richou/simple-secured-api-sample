package com.heanoria.reminders.simplesecuredapisample.datas.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserUpdate (@JsonProperty("username") val username: String)
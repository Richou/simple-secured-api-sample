package com.heanoria.reminders.simplesecuredapisample.datas.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class UserCreate(@JsonProperty("username") val username: String, @JsonProperty("password") val password: String, @JsonProperty("email") val email: String)
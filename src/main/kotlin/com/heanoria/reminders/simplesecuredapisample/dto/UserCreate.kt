package com.heanoria.reminders.simplesecuredapisample.dto

import com.fasterxml.jackson.annotation.JsonProperty


class UserCreate(@JsonProperty("username") val username: String, @JsonProperty("password") val password: String, @JsonProperty("email") val email: String)
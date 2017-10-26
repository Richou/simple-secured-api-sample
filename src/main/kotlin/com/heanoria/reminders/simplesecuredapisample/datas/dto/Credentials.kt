package com.heanoria.reminders.simplesecuredapisample.datas.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class Credentials(@JsonProperty("email") val email: String, @JsonProperty("password") val password: String)
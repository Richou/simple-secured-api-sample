package com.heanoria.reminders.simplesecuredapisample.dto

import com.fasterxml.jackson.annotation.JsonProperty


class Credentials(@JsonProperty("email") val email: String, @JsonProperty("password") val password: String)
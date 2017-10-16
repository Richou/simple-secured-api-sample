package com.heanoria.reminders.simplesecuredapisample.dto

import com.fasterxml.jackson.annotation.JsonProperty

class UserUpdate (@JsonProperty("username") val username: String)
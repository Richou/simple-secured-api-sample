package com.heanoria.reminders.simplesecuredapisample.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "token")
class TokenProperties(var validityInMinutes: Long = 120L)
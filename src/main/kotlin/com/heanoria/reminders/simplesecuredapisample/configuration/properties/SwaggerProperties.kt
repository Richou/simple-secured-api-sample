package com.heanoria.reminders.simplesecuredapisample.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "swagger")
class SwaggerProperties {
    val title = ""
    val description = ""
    val termsOfServiceUrl = ""
    val contact = ""
    val license = ""
    val licenseUrl = ""
    val version = ""
}
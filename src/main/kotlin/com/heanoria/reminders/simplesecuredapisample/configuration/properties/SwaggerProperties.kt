package com.heanoria.reminders.simplesecuredapisample.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "swagger")
class SwaggerProperties {
    var title = ""
    var description = ""
    var termsOfServiceUrl = ""
    var contact = ""
    var license = ""
    var licenseUrl = ""
    var version = ""
}
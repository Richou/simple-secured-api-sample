package com.heanoria.reminders.simplesecuredapisample.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "keypair")
class KeyPairProperties {
    var publicKey = ""
    var privateKey = ""
}
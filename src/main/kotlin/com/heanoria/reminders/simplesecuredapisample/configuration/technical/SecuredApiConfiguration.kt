package com.heanoria.reminders.simplesecuredapisample.configuration.technical

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
class SecuredApiConfiguration
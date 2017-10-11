package com.heanoria.reminders.simplesecuredapisample.configuration

import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.UserRepository
import com.heanoria.reminders.simplesecuredapisample.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserConfiguration {

    @Bean
    fun userService(userRepository: UserRepository) = UserService(userRepository)
}
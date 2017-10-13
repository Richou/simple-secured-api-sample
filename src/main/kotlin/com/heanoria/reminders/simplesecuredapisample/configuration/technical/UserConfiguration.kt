package com.heanoria.reminders.simplesecuredapisample.configuration.technical

import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.RoleRepository
import com.heanoria.reminders.simplesecuredapisample.persistence.repositories.UserRepository
import com.heanoria.reminders.simplesecuredapisample.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserConfiguration {

    @Bean
    fun userService(userRepository: UserRepository, roleRepository: RoleRepository) = UserService(userRepository, roleRepository)
}
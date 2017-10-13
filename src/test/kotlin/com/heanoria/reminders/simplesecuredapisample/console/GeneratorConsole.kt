package com.heanoria.reminders.simplesecuredapisample.console

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder



@RunWith(BlockJUnit4ClassRunner::class)
class GeneratorConsole {
    private val logger = LoggerFactory.getLogger(GeneratorConsole::class.java)

    @Test
    fun passwordGenerate() {
        val password = "azerty1234"

        val passwordEncoder = BCryptPasswordEncoder()
        val hashedPassword = passwordEncoder.encode(password)

        logger.info(hashedPassword)
    }

}
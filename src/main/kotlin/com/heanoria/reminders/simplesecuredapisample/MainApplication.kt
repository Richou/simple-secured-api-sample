package com.heanoria.reminders.simplesecuredapisample

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MainApplication

private val logger = LoggerFactory.getLogger(MainApplication::class.java)

fun main(args: Array<String>) {
    SpringApplication.run(MainApplication::class.java, *args)

    logger.info("=================================================")
    logger.info("==     Secured API Sample server is running")
    logger.info("=================================================")
}

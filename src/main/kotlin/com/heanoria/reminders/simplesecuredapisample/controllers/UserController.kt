package com.heanoria.reminders.simplesecuredapisample.controllers

import com.heanoria.reminders.simplesecuredapisample.services.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class UserController(private val userService: UserService) {

    @GetMapping("/users/{email:.+}")
    fun doGetUsers(@PathVariable email: String) = userService.getByEmail(email)
}
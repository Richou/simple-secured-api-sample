package com.heanoria.reminders.simplesecuredapisample.controllers

import com.heanoria.reminders.simplesecuredapisample.dto.UserCreate
import com.heanoria.reminders.simplesecuredapisample.services.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class UserController(private val userService: UserService) {

    @PostMapping("/users")
    fun doPostCreateUser(@RequestBody userCreate: UserCreate) = userService.createUser(userCreate)

    @GetMapping("/users/{email:.+}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    fun doGetUsers(@PathVariable email: String) = userService.getByEmail(email)
}
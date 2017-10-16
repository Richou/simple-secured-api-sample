package com.heanoria.reminders.simplesecuredapisample.controllers

import com.heanoria.reminders.simplesecuredapisample.dto.UserCreate
import com.heanoria.reminders.simplesecuredapisample.dto.UserUpdate
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
    fun doGetUsers(@PathVariable email: String, @RequestHeader(value = "Authorization") authorization: String) = userService.getByEmail(email)

    @DeleteMapping("/users/{email:.+}")
    @PreAuthorize("#email == authentication.principal or hasRole('ROLE_ADMIN')")
    fun doDeleteUsers(@PathVariable email: String, @RequestHeader("Authorization") authorization: String) = userService.deleteUser(email)

    @PutMapping("/users/{email:.+}")
    @PreAuthorize("#email == authentication.principal or hasRole('ROLE_ADMIN')")
    fun doEditUsers(@PathVariable email: String, @RequestBody userUpdate: UserUpdate, @RequestHeader("Authorization") authorization: String) = userService.editUserByEmail(email, userUpdate)
}
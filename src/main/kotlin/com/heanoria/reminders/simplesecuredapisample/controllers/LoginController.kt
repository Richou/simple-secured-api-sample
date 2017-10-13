package com.heanoria.reminders.simplesecuredapisample.controllers

import com.heanoria.reminders.simplesecuredapisample.dto.Credentials
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class LoginController {

    @PostMapping("/login")
    fun doPostLogin(@RequestBody credentials: Credentials) = null
}
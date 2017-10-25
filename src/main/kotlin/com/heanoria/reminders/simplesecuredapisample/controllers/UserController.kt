package com.heanoria.reminders.simplesecuredapisample.controllers

import com.heanoria.reminders.simplesecuredapisample.dto.UserCreate
import com.heanoria.reminders.simplesecuredapisample.dto.UserUpdate
import com.heanoria.reminders.simplesecuredapisample.services.ArticleService
import com.heanoria.reminders.simplesecuredapisample.services.UserService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1")
class UserController(private val userService: UserService, private val articleService: ArticleService) {

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

    @GetMapping("/users/{id}/articles")
    @ApiImplicitParams(ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
        ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
        ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported."))
    fun doGetUserArticles(pageable: Pageable, @PathVariable id: UUID) = articleService.getArticlesForUser(pageable, id)
}
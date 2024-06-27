package com.example.messageboard.controllers

import com.example.messageboard.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.example.messageboard.models.User
import com.example.messageboard.util.getCurrentUserEmail
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/register")
    fun register(@RequestBody userDto: UserDto): ResponseEntity<User> {
        return try {
            val user = userService.registerUser(userDto.viewName, userDto.email, userDto.password)
            ResponseEntity(user, HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            ResponseEntity(HttpStatus.CONFLICT)
        }
    }

    @GetMapping("/info")
    fun getUserInfo(): ResponseEntity<User> {
        val email = getCurrentUserEmail() // 現在ログイン中のユーザーのメールを取得する関数を実装する必要があります
        val user = userService.getUserByEmail(email)
        return if (user != null) {
            ResponseEntity(user, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
data class UserDto(val viewName: String, val email: String, val password: String)
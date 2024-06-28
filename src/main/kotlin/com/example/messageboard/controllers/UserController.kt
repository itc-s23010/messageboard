package com.example.messageboard.controllers

import com.example.messageboard.form.GetUserInfoResponse
import com.example.messageboard.form.PostUserRegisterRequest
import com.example.messageboard.infrastructurs.dao.UsersTable.viewName
import com.example.messageboard.services.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
@CrossOrigin
class UserController<MessageBoardUserDetails>(
    private val userService: UserService,
) {

    @PostMapping("/register")
    fun register(@RequestBody request: PostUserRegisterRequest) {
        run {
            userService.registerUser(request.viewName, request.email, request.password)
        }
    }
//    @PostMapping("/register")
//    fun register(@RequestBody userDto: UserDto): ResponseEntity<User> {
//        return try {
//            val user = userService.registerUser(userDto.viewName, userDto.email, userDto.password)
//            ResponseEntity(user, HttpStatus.OK)
//        } catch (e: IllegalArgumentException) {
//            ResponseEntity(HttpStatus.CONFLICT)
//        }
//    }

    @GetMapping("/info")
    fun getInfo(
        @AuthenticationPrincipal user: MessageBoardUserDetails
    ): GetUserInfoResponse {
        return userService.find(user.getId()).run {
            GetUserInfoResponse(id, viewName)
        }
    }
//    @GetMapping("/info")
//    fun getUserInfo(): ResponseEntity<User> {
//        val email = getCurrentUserEmail() // 現在ログイン中のユーザーのメールを取得する関数を実装する必要があります
//        val user = userService.getUserByEmail(email)
//        return if (user != null) {
//            ResponseEntity(user, HttpStatus.OK)
//        } else {
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }
}
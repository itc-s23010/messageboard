package com.example.messageboard.controllers

import com.example.messageboard.services.ThreadService
import org.springframework.web.bind.annotation.*
import com.example.messageboard.models.Thread
import com.example.messageboard.services.UserService
import com.example.messageboard.util.getCurrentUserEmail
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@RestController
@RequestMapping("/threads")
class ThreadController(
    private val threadService: ThreadService,
    private val userService: UserService
) {

    @PostMapping("/create")
    fun createThread(@RequestBody threadDto: ThreadDto): ResponseEntity<Thread> {
        val email = getCurrentUserEmail() //現在ログイン中のユーザーのメールを取得する関数を実装する必要があります
        val user = userService.getUserByEmail(email) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val thread = threadService.createThread(threadDto.title, user)
        return ResponseEntity(thread, HttpStatus.CREATED)
    }

    @GetMapping("/list")
    fun listThreads(): ResponseEntity<List<Thread>> {
        val threads = threadService.getThreads()
        return ResponseEntity(threads, HttpStatus.OK)
    }
}

data class ThreadDto(val title: String)
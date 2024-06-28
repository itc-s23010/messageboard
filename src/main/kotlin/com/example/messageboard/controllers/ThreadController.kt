package com.example.messageboard.controllers

import com.example.messageboard.form.*
import com.example.messageboard.infrastructurs.dao.ThreadsTable.title
import com.example.messageboard.infrastructurs.dao.ThreadsTable.updatedAt
import com.example.messageboard.services.ThreadService
import org.springframework.web.bind.annotation.*
import com.example.messageboard.models.Thread
import com.example.messageboard.services.UserService
import com.example.messageboard.util.getCurrentUserEmail
import org.example.messageboard.MessageBoardApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import kotlin.concurrent.thread


@RestController
@RequestMapping("/threads")
@CrossOrigin
class ThreadController(
    private val threadService: ThreadService,
    private val userService: UserService
) {
    @GetMapping("/list")
    fun getList(): GetThreadListResponse {
        val threadList = service.getList().map(::ThreadInfo)
        return GetThreadListResponse(threadList)
    }


//    @GetMapping("/list")
//    fun listThreads(): ResponseEntity<List<Thread>> {
//        val threads = threadService.getThreads()
//        return ResponseEntity(threads, HttpStatus.OK)
//    }

    @PostMapping("/create")
    fun create(
        @RequestBody body: PostThreadRequest,
        @AuthenticationPrincipal user: MessageBoardApplication
    ): CreatedThreadResponse {
        val newId = service.newPost(body.title, body.message, user.id)
        return CreatedThreadResponse(newId)
    }
//    @PostMapping("/create")
//    fun createThread(@RequestBody threadDto: ThreadDto): ResponseEntity<Thread> {
//        val email = getCurrentUserEmail() //現在ログイン中のユーザーのメールを取得する関数を実装する必要があります
//        val user = userService.getUserByEmail(email) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
//        val thread = threadService.createThread(threadDto.title, user)
//        return ResponseEntity(thread, HttpStatus.CREATED)
//    }

    @PutMapping("/update/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody body: PutThreadUpdateRequest,
        @AuthenticationPrincipal user: MessageBoardUserDetails
    ): ThreadUpdateResponse {
        val thread = service.updateTitle(id, body.title, user.id)
        return thread.run { ThreadUpdateResponse(id, title) }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteThread(
        @PathVariable id: Long,
        @AuthenticationPrincipal user: MessageBoardUserDetails
    ): ThreadDeleteResponse {
        val result = service.delete(id, user.id)
        return result.run {
            ThreadDeleteResponse(id, title, updatedAt)
        }
    }
}
data class ThreadDto(val title: String)
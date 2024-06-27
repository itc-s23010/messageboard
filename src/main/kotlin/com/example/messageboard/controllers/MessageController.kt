package com.example.messageboard.controllers

import com.example.messageboard.models.Message
import com.example.messageboard.models.User
import com.example.messageboard.services.MessageService
import com.example.messageboard.services.ThreadService
import com.example.messageboard.services.UserService
import com.example.messageboard.util.getCurrentUserEmail
import com.sun.nio.sctp.MessageInfo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/message")
@CrossOrigin
class MessageController(
    private val messageService: MessageService,
    private val threadService: ThreadService,
    private val userService: UserService
) {
    @GetMapping("/list/thread/{threadId}")
    fun listMessages(@PathVariable threadId: Long): ResponseEntity<List<Message>> {
        val thread = threadService.findById(threadId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val messages = messageService.getMessages(thread)
        return ResponseEntity(messages, HttpStatus.OK)
    }
//    @GetMapping("/list/thread/{threadId}")
//    fun listMessages(@PathVariable threadId: Long): ResponseEntity<List<Message>> {
//        val thread = threadService.findById(threadId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
//        val messages = messageService.getMessages(thread)
//        return ResponseEntity(messages, HttpStatus.OK)
//    }

    @Suppress("NAME_SHADOWING")
    @PostMapping("/post/thread/{threadId}")
    fun postMessage(
        @PathVariable(value = "threadId") threadId: Long,
        @RequestBody messageDto: MessageDto,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<Message> {
        val email = getCurrentUserEmail()
        val user = userService.getUserByEmail(email) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val thread = threadService.findById(threadId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val message = messageService.postMessage(thread, user, messageDto.message)
        return ResponseEntity(message, HttpStatus.CREATED)
    }
    //@PostMapping("/post/thread/{threadId}")
    //fun postMessage(@PathVariable threadId: Long, @RequestBody messageDto: MessageDto): ResponseEntity<Message> {
    //    val email = getCurrentUserEmail()
    //    val user = userService.getUserByEmail(email) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
    //    val thread = threadService.findById(threadId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
    //    val message = messageService.postMessage(thread, user, messageDto.message)
    //    return ResponseEntity(message, HttpStatus.CREATED)
    //}

    @PutMapping("/put/thread/{threadId}")
    fun putMessage(
        @PathVariable(value = "threadId") threadId: Long,
        @RequestBody messageDto: MessageDto,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<Message> {
        val updatedMessage = messageService.updateMessage(threadId, messageDto.message, user.id)
        return updatedMessage.run {
            MessageUpdateResponse(
                id = id, threadId = thread.id, message = message, updatedAt = updatedAt
            )
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteMessage(
        @PathVariable("id") id: Long,
        @AuthenticationPrincipal user: User
    ):  ResponseEntity<Message> {
        val result = messageService.deleteMessage(id, user.id)
        return result.run {
            MessageDeleteResponse(id, thread.id, updatedAt)
        }
    }

}
data class MessageDto(val message: String)
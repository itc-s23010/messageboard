package com.example.messageboard.services

import com.example.messageboard.models.Message
import com.example.messageboard.repositories.MessageRepository
import org.springframework.stereotype.Service
import com.example.messageboard.models.Thread
import com.example.messageboard.models.User
import org.aspectj.bridge.Message
import java.time.LocalDateTime

@Service
class MessageService(
    private val messageRepository: MessageRepository
) {

    fun postMessage(thread: Thread, user: User, messageContent: String): Message {
        val message = Message(thread = thread, user = user, message = messageContent, postedAt = LocalDateTime.now(), updatedAt = LocalDateTime.now())
        return messageRepository.save(message)
    }

    fun getMessages(thread: Thread): List<Message> {
        return messageRepository.findByThreadAndDeletedFalseOrderByPostedAtAsc(thread)
    }
}
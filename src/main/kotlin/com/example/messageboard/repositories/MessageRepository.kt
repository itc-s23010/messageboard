package com.example.messageboard.repositories

import com.example.messageboard.models.Messages
import com.example.messageboard.models.Threads
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface MessageRepository {
    //fun findByThreadAndDeletedFalseOrderByPostedAtAsc(thread: Thread): List<Message>
    fun createMessage(message: Messages): Messages

    fun getMessageById(id: Long): Messages?

    fun getMessageByThreadId(threadId: Long): List<Messages>

    fun updateMessage(message: Messages): Messages

    fun deleteMessage(id: Long)
}
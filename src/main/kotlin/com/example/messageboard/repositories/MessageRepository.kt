package com.example.messageboard.repositories

import com.example.messageboard.models.Message
import com.example.messageboard.models.Thread
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, Long> {
    fun findByThreadAndDeletedFalseOrderByPostedAtAsc(thread: Thread): List<Message>
}
package com.example.messageboard.services

import com.example.messageboard.models.Thread
import com.example.messageboard.models.User
import com.example.messageboard.repositories.ThreadRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ThreadService(
    private val threadRepository: ThreadRepository
) {

    fun createThread(title: String, user: User): Thread {
        val thread = Thread(title = title, user = user, createAt = LocalDateTime.now(), updateAt = LocalDateTime.now())
        return threadRepository.save(thread)
    }

    fun getThreads(): List<Thread> {
        return threadRepository.findByDeletedFalseOrderByUpdatedAtDesc()
    }

    fun findById(threadId: Long): Thread? {
        return threadRepository.findById(threadId).orElse(null)
    }
}
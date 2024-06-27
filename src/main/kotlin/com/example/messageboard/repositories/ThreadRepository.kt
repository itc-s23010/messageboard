package com.example.messageboard.repositories

import com.example.messageboard.models.Thread
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ThreadRepository : JpaRepository<Thread, Long> {
    fun findByDeletedFalseOrderByUpdatedAtDesc(): List<Thread>
}
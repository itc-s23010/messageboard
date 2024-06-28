package com.example.messageboard.repositories

//import com.example.messageboard.models.Thread
import com.example.messageboard.models.Threads
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface ThreadRepository {
    fun createThread(thread: Threads): Threads
    fun getThreadById(id: Long): Threads?
    fun getAllThreads(): List<Threads>
    fun updateThread(thread: Threads): Threads
    fun deleteThread(id: Long)
}
//@Repository
//interface ThreadRepository : JpaRepository<Thread, Long> {
//    fun findByDeletedFalseOrderByUpdatedAtDesc(): List<Thread>
//}
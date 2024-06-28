package com.example.messageboard.models

import jakarta.persistence.*
import java.time.LocalDateTime

data class Messages(
    val id: Long,
    val threadId: Long,
    val userId: Long,
    val message: String,
    val postedAt: LocalDateTime,
    val updateAt: LocalDateTime,
    val deleted: Boolean
)
//@Entity
//@Table(name = "messages")
//data class Message(
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Long = 0,
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "thread_id", nullable = false)
//    val thread: Thread = Thread(),
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    val user: User = User(),
//
//    @Column(nullable = false)
//    val message: String = "",
//
//    @Column(nullable = false)
//    val postedAt: LocalDateTime = LocalDateTime.now(),
//
//    @Column(nullable = false)
//    val updatedAt: LocalDateTime = LocalDateTime.now(),
//
//    @Column(nullable = false)
//    val deleted: Boolean = false,
//)

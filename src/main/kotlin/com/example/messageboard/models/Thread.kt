package com.example.messageboard.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "threads")
data class Thread(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    val title: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User = User(),

    @Column(nullable = false)
    val createAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val updateAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val deleted: Boolean = false
)

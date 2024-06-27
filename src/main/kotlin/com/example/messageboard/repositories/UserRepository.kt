package com.example.messageboard.repositories

import com.example.messageboard.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}
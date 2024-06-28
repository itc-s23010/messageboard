package com.example.messageboard.repositories

//import com.example.messageboard.models.User
import com.example.messageboard.models.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UserRepository {
    fun findByEmail(email: String): Users?

    fun findById(id: Long): Users?

    fun createUser(user: Users): Users

    fun updateUser(user: Users): Users

    fun deleteUser(id: Long)

    fun save(user: Users): Users
}
//@Repository
//interface UserRepository : JpaRepository<User, Long> {
//    fun findByEmail(email: String): User?
//}
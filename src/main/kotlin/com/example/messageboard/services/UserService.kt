package com.example.messageboard.services

import com.example.messageboard.models.Users
import com.example.messageboard.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User not found with email: $email")
        return org.springframework.security.core.userdetails.User(
            user.email,
            user.password,
            emptyList()
        )
    }

    fun registerUser(viewName: String, email: String, password: String): Users {
        if (userRepository.findByEmail(email) != null) {
            throw IllegalArgumentException("Email already registerd")
        }
        val hashedPassword = passwordEncoder.encode(password)
        val user = Users(viewName = viewName, email = email, password = hashedPassword)
        return userRepository.save(user)
    }

    fun getUserByEmail(email: String): Users? {
        return userRepository.findByEmail(email)
    }
}
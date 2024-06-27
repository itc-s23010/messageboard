package com.example.messageboard.util

import org.springframework.context.annotation.Bean
import org.springframework.security.core.context.SecurityContextHolder

@Bean
fun getCurrentUserEmail(): String {
    val authentication = SecurityContextHolder.getContext().authentication
    return authentication?.name ?: throw IllegalStateException("No user is authenticated")
}
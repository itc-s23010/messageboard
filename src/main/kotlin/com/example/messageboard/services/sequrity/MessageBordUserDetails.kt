package com.example.messageboard.services.sequrity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class MessageBordUserDetails(
    private val id: Long,
    private val email: String,
    private val password: String,
    private val viewName: String,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    fun getId(): Long {
        return id
    }

    fun getViewName(): String {
        return viewName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }
    override fun isEnabled(): Boolean {
        return true
    }
}
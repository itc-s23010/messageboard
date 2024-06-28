package com.example.messageboard.form

import jakarta.persistence.metamodel.SingularAttribute
import kotlinx.serialization.Serializable
import org.springframework.data.jpa.domain.AbstractPersistable

@Serializable
data class PostUserRegisterRequest(
    val viewName: String,
    val email: String,
    val password: String
)

@Serializable
data class GetUserInfoResponse(
    val id: SingularAttribute<AbstractPersistable<java.io.Serializable>, java.io.Serializable>,
    val viewName: String,
)
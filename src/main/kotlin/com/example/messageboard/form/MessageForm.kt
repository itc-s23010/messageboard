package com.example.messageboard.form

import java.time.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class GetMessageListResponse(
    val threadId: Long,
    val title: String,
    val messages: List<MessageInfo>
)

@Serializable
data class MessageInfo(
    val id: Long,
    val userId: Long?,
    val username: String?,
    val message: String?,
    val postedAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    constructor(model: Message) : this(
        id = model.id,
        userId = if (model.deleted) null else model.user.id,
        username = if (model.deleted) null else model.user.viewName,
        message = if (model.deleted) null else model.message,
        postedAt = model.updatedAt,
        updatedAt = model.updatedAt,
    )
}

@Serializable
data class PostMessageRequest(
    val message: String
)

@Serializable
data class PostedMessageResponse(
    val id: Long,
    val threadId: Long,
    val userId: Long,
    val postedAt: LocalDateTime,
) {
    constructor(model: MessageWithThread) : this(
        id = model.id,
        threadId = model.thread.id,
        userId = model.user.id,
        postedAt = model.postedAt
    )
}

@Serializable
data class PutMessageUpdateRequest(
    val message: String,
)

@Serializable
data class MessageUpdateResponse(
    val id: Long,
    val threadId: Long,
    val message: String,
    val updatedAt: LocalDateTime,
)

@Serializable
data class MessageDeleteResponse(
    val id: Long,
    val threadId: Long,
    val updatedAt: LocalDateTime,
)
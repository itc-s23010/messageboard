package com.example.messageboard.infrastructurs.dao

import com.example.messageboard.models.Messages
import org.springframework.data.jpa.domain.AbstractPersistable_.id


class MessagesEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<MessagesEntity>(MessagesTables)

    var threadId by ThreadsEntity referencedOn MessagesTables.threadId
    var userId by UsersEntity referencedOn MessagesTables.userId
    var message by MessagesTables.message
    var postedAt by MessagesTables.postedAt
    var updatedAt by MessagesTables.updatedAt
    var deleted by MessagesTables.deleted

    fun toMessage(): Messages {
        return Messages(
            id.value,
            threadId.id.value,
            userId.id.value,
            message,
            postedAt,
            updatedAt,
            deleted
        )
    }
}
package com.example.messageboard.infrastructurs.repository

import com.example.messageboard.infrastructurs.dao.MessagesEntity
import com.example.messageboard.infrastructurs.dao.MessagesTables
import com.example.messageboard.infrastructurs.dao.MessagesTables.deleted
import com.example.messageboard.infrastructurs.dao.MessagesTables.postedAt
import com.example.messageboard.infrastructurs.dao.MessagesTables.threadId
import com.example.messageboard.infrastructurs.dao.MessagesTables.updatedAt
import com.example.messageboard.infrastructurs.dao.MessagesTables.userId
import com.example.messageboard.infrastructurs.dao.ThreadsEntity
import com.example.messageboard.infrastructurs.dao.UsersEntity
import com.example.messageboard.models.Messages
import com.example.messageboard.repositories.MessageRepository
import org.springframework.stereotype.Repository

@Repository
class MessagesRepositoryImpl: MessageRepository {
    override fun createMessage(message: Messages): Messages {
        return transaction {
            val newMessage = MessagesEntity.new {
                threadId = ThreadsEntity[message.threadId]
                userId = UsersEntity[message.userId]
                this.message = message.message
                postedAt = message.postedAt
                updatedAt = message.updateAt
                deleted = message.deleted
            }
            newMessage.toMessage()
        }
    }

    override fun getMessageById(id: Long): Messages? {
        return transaction {
            val message = MessagesEntity.findById(id)
            message?.toMessage()
        }
    }

    override fun getMessageByThreadId(threadId: Long): List<Messages> {
        return transaction {
            MessagesEntity.find { MessagesTables.threadId eq threadId }
                .map { it.toMessage() }
        }
    }

    override fun updateMessage(message: Messages): Messages {
        return transaction {
            val messageEntity = MessagesEntity.findById(message.id)
                ?: throw IllegalArgumentException("Message not found with id: ${message.id}")

            messageEntity.apply {
                threadId = ThreadsEntity[message.threadId]
                userId = UsersEntity[message.userId]
                this.message = message.message
                postedAt = message.postedAt
                updatedAt = message.updateAt
                deleted = message.deleted
            }

            messageEntity.toMessage()
        }
    }

    override fun deleteMessage(id: Long) {
        transaction {
            MessagesEntity.findById(id)?.delete()
        }
    }
}
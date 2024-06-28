package com.example.messageboard.infrastructurs.dao

import com.example.messageboard.models.Threads

import org.springframework.data.jpa.domain.AbstractPersistable_.id

class ThreadsEntity(id: EntityID<Long> ) : LongEntity(id) {
    companion object : LongEntityClass<ThreadsEntity>(ThreadsTable)

    var title by ThreadsTable.title
    var userId by UsersEntity referencedOn ThreadsTable.userId
    var createdAt by ThreadsTable.createdAt
    var updatedAt by ThreadsTable.updatedAt
    var deleted by ThreadsTable.deleted

    fun toThread(): Threads {
        return Threads(
            id.value,
            title,
            userId.id.value,
            createdAt,
            updatedAt,
            deleted
        )
    }
}
package com.example.messageboard.infrastructurs.dao



object ThreadsTable : LongIdTable("thread") {
    val title = varchar("title", 256)
    val userId = reference("user_id", UsersTable)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
    val deleted = bool("deleted").default(false)
}
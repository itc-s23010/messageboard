package com.example.messageboard.infrastructurs.dao

import com.example.messageboard.models.Users

class UsersEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UsersEntity>(UsersTable)

    var viewName by UsersTable.viewName
    var email by UsersTable.email
    var password by UsersTable.password

    fun toUser(): Users {
        return Users(
            viewName,
            email,
            password
        )
    }
}
package com.example.messageboard.infrastructurs.dao

import com.example.messageboard.infrastructurs.dao.UsersTable

object UsersTable : LongIdTable("user") {
    var viewName = varchar("view_name", 32)
    var email = varchar("email", 256)
    var password = varchar("password", 128)
}
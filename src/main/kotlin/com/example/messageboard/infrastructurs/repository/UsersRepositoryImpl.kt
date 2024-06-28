package com.example.messageboard.infrastructurs.repository

import com.example.messageboard.infrastructurs.dao.UsersEntity
import com.example.messageboard.infrastructurs.dao.UsersTable
import com.example.messageboard.infrastructurs.dao.UsersTable.email
import com.example.messageboard.infrastructurs.dao.UsersTable.password
import com.example.messageboard.infrastructurs.dao.UsersTable.viewName
import com.example.messageboard.models.Users
import com.example.messageboard.repositories.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UsersRepositoryImpl : UserRepository {
    override fun findByEmail(email: String): Users? {
        return transaction {
            UsersEntity.find { UsersTable.email eq email
            }.singleOrNull()?.toUser()
        }
    }

    override fun findById(id: Long): Users? {
        return transaction {
            UsersEntity.findById(id)?.toUser()
        }
    }

    override fun createUser(user: Users): Users {
        return transaction {
            UsersEntity.new {
                this.email = user.email
                this.password = user.password
                this.viewName = user.viewName
            }.toUser()
        }
    }

    override fun updateUser(user: Users): Users {
        return transaction {
            val userEntity = UsersEntity.findById(user.id)
                ?: throw IllegalArgumentException("User not found with id: ${user.id}")
            userEntity.apply {
                email = user.email
                password = user.password
                viewName = user.viewName
            }
            userEntity.toUser()
        }
    }

    override fun deleteUser(id: Long) {
        transaction {
            val userEntity = UsersEntity.findById(id)
                ?: throw IllegalArgumentException("User not found with id: $id")
            userEntity.delete()
        }
    }

    override fun save(user: Users): Users {
        return if (user.id == 0L) {
            createUser(user)
        } else {
            updateUser(user)
        }
    }
}
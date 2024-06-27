package org.example.messageboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessageBoardApplication

fun main(args: Array<String>) {
    runApplication<MessageBoardApplication>(*args)
}
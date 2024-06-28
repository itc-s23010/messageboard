package com.example.messageboard.models

data class Users(
    val viewName: String,
    val email: String,
    val password: String
)

//@Entity
//@Table(name = "users")
//data class User(
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Long = 0,
//
//    @Column(nullable = false, unique = true)
//    val email: String = "",
//
//    @Column(nullable = false)
//    val password: String = "",
//
//    @Column(nullable = false)
//    val viewName: String = ""
//)
interface User {

}

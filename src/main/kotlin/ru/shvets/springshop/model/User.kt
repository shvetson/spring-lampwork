package ru.shvets.springshop.model

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  11.01.2023 07:58
 */
data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
package ru.shvets.springshop.model

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  11.01.2023 07:58
 */
/**
 * A DTO for the {@link ru.shvets.springshop.dto.UserEntity} entity
 */
data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
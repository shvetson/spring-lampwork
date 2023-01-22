package ru.shvets.springshop.dto

import java.io.Serializable
import javax.persistence.*

/**
 * A DTO for the {@link ru.shvets.springshop.entity.OrderEntity} entity
 */
data class OrderDto(
    var id: Long? = null,
    var client: ClientDto? = null,
    var created: Long? = null,
    var deadline: Long? = null
) : Serializable
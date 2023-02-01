package ru.shvets.springshop.dto

import java.io.Serializable
import java.util.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  18.01.2023 00:07
 */

data class ClientDto(
    var id: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var created: Long? = Date().time,
    var updated: Long? = Date().time,
    var address: AddressDto? = AddressDto(),
    var email: String? = null,
    var phone: String? = null,
    var telegram: String? = null,
    var whatsApp: String? = null,
    var description: String? = null,
//    var shopping: MutableList<ProductDto>? = mutableListOf()
)
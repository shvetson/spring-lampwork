package ru.shvets.springshop.dto

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  21.01.2023 09:08
 */

data class AddressDto(
    var id: Long? = null,
    var code: String? = null,
    var city: String? = null,
    var region: String? = null,
    var area: String? = null,
    var street: String? = null,
    var building: String? = null,
    var housing: String? = null,
    var flat: Int? = null,
)
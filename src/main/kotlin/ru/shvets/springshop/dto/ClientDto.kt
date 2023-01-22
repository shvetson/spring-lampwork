package ru.shvets.springshop.dto

import ru.shvets.springshop.dto.AddressDto.Companion.toDto
import ru.shvets.springshop.dto.ProductDto.Companion.toDto
import ru.shvets.springshop.entity.ClientEntity
import ru.shvets.springshop.util.Utils.toDate
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
    var created: String? = null,
    var updated: String? = null,
    var address: AddressDto = AddressDto(),
    var email: String? = null,
    var phone: String? = null,
    var telegram: String? = null,
    var whatsApp: String? = null,
    var description: String? = null,
//    var shoppingList: List<ProductDto> = listOf()
) {

    fun toEntity(): ClientEntity {
        return ClientEntity(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            created = this.created?.toDate()?.time,
            updated = this.updated?.toDate()?.time,
            address = this.address.toEntity(),
            email = this.email,
            phone = this.phone,
            telegram = this.telegram,
            whatsApp = this.whatsApp,
            description = this.description,
//            shoppingList = this.shoppingList.map { it.toEntity() }
        )
    }

    companion object {
        fun ClientEntity.toDto(): ClientDto =
            ClientDto(
                id = this.id,
                firstName = this.firstName,
                lastName = this.lastName,
                created = this.created?.toDate(),
                updated = this.updated?.toDate(),
                address = this.address.toDto(),
                email = this.email,
                phone = this.phone,
                telegram = this.telegram,
                whatsApp = this.whatsApp,
                description = this.description,
//                shoppingList = this.shoppingList.toList().map { it.toDto() }
            )
    }
}
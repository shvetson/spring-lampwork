package ru.shvets.springshop.mapper

import ru.shvets.springshop.dto.AddressDto
import ru.shvets.springshop.dto.ClientDto
import ru.shvets.springshop.entity.ClientEntity
import ru.shvets.springshop.mapper.AddressMapper.Companion.toDto
import ru.shvets.springshop.mapper.AddressMapper.Companion.toEntity
import ru.shvets.springshop.mapper.ProductMapper.Companion.toDto
import ru.shvets.springshop.mapper.ProductMapper.Companion.toEntity

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  28.01.2023 20:31
 */

class ClientMapper {
    companion object {
        fun ClientEntity.toDto(): ClientDto {
            return ClientDto(
                id = this.id,
                firstName = this.firstName,
                lastName = this.lastName,
                created = this.created,
                updated = this.updated,
                address = this.address?.toDto(),
                email = this.email,
                phone = this.phone,
                telegram = this.telegram,
                whatsApp = this.whatsApp,
                description = this.description,
//                shopping = this.shopping?.map{ it.toDto()}?.toMutableList()
            )
        }

        fun ClientDto.toEntity(): ClientEntity {
            return ClientEntity(
                id = this.id,
                firstName = this.firstName,
                lastName = this.lastName,
                created = this.created,
                updated = this.updated,
                address = this.address?.toEntity(),
                email = this.email,
                phone = this.phone,
                telegram = this.telegram,
                whatsApp = this.whatsApp,
                description = this.description,
//                shopping = this.shopping?.map {it.toEntity()}?.toMutableList()
            )
        }
    }
}
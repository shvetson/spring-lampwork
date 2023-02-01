package ru.shvets.springshop.mapper

import ru.shvets.springshop.dto.AddressDto
import ru.shvets.springshop.entity.AddressEntity

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  28.01.2023 20:47
 */

class AddressMapper {
    companion object {
        fun AddressEntity.toDto(): AddressDto {
            return AddressDto(
                id = this.id,
                code = this.code,
                city = this.city,
                region = this.region,
                area = this.area,
                street = this.street,
                building = this.building,
                housing = this.housing,
                flat = this.flat
            )
        }

        fun AddressDto.toEntity(): AddressEntity {
            return AddressEntity(
                id = this.id,
                code = this.code,
                city = this.city,
                region = this.region,
                area = this.area,
                street = this.street,
                building = this.building,
                housing = this.housing,
                flat = this.flat
            )
        }
    }
}
package ru.shvets.springshop.dto

import ru.shvets.springshop.entity.AddressEntity

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
//    val client: ClientEntity
) {

    fun toEntity(): AddressEntity {
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
    }
}
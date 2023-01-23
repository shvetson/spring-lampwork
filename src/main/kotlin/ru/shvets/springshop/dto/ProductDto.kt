package ru.shvets.springshop.dto

import ru.shvets.springshop.dto.ClientDto.Companion.toDto
import ru.shvets.springshop.entity.ProductEntity
import ru.shvets.springshop.entity.ProductTypeEntity
import ru.shvets.springshop.model.ProductState
import ru.shvets.springshop.util.Utils.toDate

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  26.09.2022 14:24
 */

data class ProductDto(
    var id: Long? = null,
    var name: String? = null,
    var created: String? = null,
    var price: Int? = null,
    var oldPrice: Int? = null,
    var state: ProductState = ProductState.NEW,
    var sold: String? = null,
    var image: String? = null,
    var description: String? = null,
    var productType: ProductTypeEntity = ProductTypeEntity(),
    var client: ClientDto? = null,
    var enabled: Boolean? = true
) {

    fun toEntity(): ProductEntity {
        return ProductEntity(
            id = this.id,
            name = this.name,
            created = this.created?.toDate()?.time,
            price = this.price,
            oldPrice = this.oldPrice,
            state = this.state,
            sold = this.sold?.toDate()?.time,
            image = this.image,
            description = this.description,
            productType = this.productType,
            client = this.client?.toEntity(),
            enabled = this.enabled
        )
    }

    companion object {
        fun ProductEntity.toDto(): ProductDto {
            return ProductDto(
                id = this.id,
                name = this.name,
                created = this.created?.toDate(),
                price = this.price,
                oldPrice = this.oldPrice,
                state = this.state,
                sold = if (this.sold != 0L) this.sold?.toDate() else "",
                image = this.image,
                description = this.description,
                productType = this.productType,
                client = this.client?.toDto(),
                enabled = this.enabled
            )
        }
    }
}

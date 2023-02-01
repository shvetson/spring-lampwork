package ru.shvets.springshop.api.response

import ru.shvets.springshop.dto.ProductDto
import ru.shvets.springshop.model.ProductState
import ru.shvets.springshop.entity.ProductTypeEntity
import ru.shvets.springshop.util.Utils.toDate

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  11.01.2023 08:56
 */

data class ProductsResponse (
    val id: Long?,
    val name: String?,
    val created: String?,
    val price: Int?,
    val oldPrice: Int?,
    val state: ProductState?,
    val sold: String?,
    val image: String?,
    val description: String?,
    val productType: ProductTypeEntity?
) {
        companion object {
        fun ProductDto.toResponse(): ProductsResponse {
            return ProductsResponse(
                id = this.id,
                name = this.name,
                created = this.created?.toDate(),
                price = this.price,
                oldPrice = this.oldPrice,
                state = this.state,
                sold = this.sold?.toDate(),
                image = this.image,
                description = this.description,
                productType = this.productType
            )
        }
    }
}
package ru.shvets.springshop.dto

import ru.shvets.springshop.entity.Product
import ru.shvets.springshop.entity.ProductState
import ru.shvets.springshop.entity.ProductType
import ru.shvets.springshop.util.Utils.toDate

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  26.09.2022 14:24
 */

data class ProductDto(
    val id: Long,
    val name: String,
    val created: String,
    val price: Float,
    val oldPrice: Float = 0F,
    val state: ProductState,
    val sold: String,
    val image: String,
    val description: String,
    val productType: ProductType
) {

    companion object {
        fun Product.toDto(): ProductDto {
            return ProductDto(
                id = this.id,
                name = this.name,
                created = this.created.toDate(),
                price = this.price,
                oldPrice = this.oldPrice,
                state = this.state,
                sold = this.sold.toDate(),
                image = this.image,
                description = this.description,
                productType = this.productType
            )
        }
    }
}

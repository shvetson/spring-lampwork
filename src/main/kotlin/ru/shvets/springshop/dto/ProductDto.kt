package ru.shvets.springshop.dto

import ru.shvets.springshop.entity.ProductState
import ru.shvets.springshop.entity.ProductType

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
)

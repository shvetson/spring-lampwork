package ru.shvets.springshop.dto

import ru.shvets.springshop.entity.ProductTypeEntity
import ru.shvets.springshop.model.ProductState
import java.io.Serializable
import java.util.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  26.09.2022 14:24
 */

data class ProductDto(
    var id: Long? = null,
    var name: String? = null,
    var created: Long? = Date().time,
    var price: Int? = 0,
    var oldPrice: Int? = 0,
    var state: ProductState? = ProductState.NEW,
    var sold: Long? = 0L,
    var image: String? = null,
    var description: String? = null,
    var productType: ProductTypeEntity? = ProductTypeEntity(),
    var client: ClientDto? = null,
    var enabled: Boolean? = true
)
package ru.shvets.springshop.service

import org.springframework.web.multipart.MultipartFile
import ru.shvets.springshop.api.response.ProductsResponse
import ru.shvets.springshop.dto.ProductDto
import ru.shvets.springshop.entity.ProductEntity
import ru.shvets.springshop.entity.ProductTypeEntity

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  03.10.2022 15:40
 */

interface ProductService{
    fun getAll(): List<ProductDto>
    fun getAllApi(): List<ProductsResponse>
    fun getAllByMap(): LinkedHashMap<ProductTypeEntity, List<ProductDto>>
    fun getAllByFilter(
        listTypesId: List<Long>,
        availableProducts: Boolean,
        saleProducts: Boolean,
        newProducts: Boolean,
        priceRangeProducts: Long
    ): LinkedHashMap<ProductTypeEntity, List<ProductDto>>
    fun getById(id: Long): ProductEntity
    fun create(): ProductEntity
    fun save(product: ProductEntity)
    fun delete(id: Long)
    fun deleteFileByProductId(id: Long): Boolean
    fun transferFile(file: MultipartFile): String
    fun maxPrice(): Int
    fun minPrice(): Int
}
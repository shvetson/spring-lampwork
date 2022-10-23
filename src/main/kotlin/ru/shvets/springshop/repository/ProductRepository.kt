package ru.shvets.springshop.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.shvets.springshop.entity.Product
import ru.shvets.springshop.entity.ProductState
import ru.shvets.springshop.entity.ProductType

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  30.09.2022 20:06
 */

@Repository
interface ProductRepository: JpaRepository<Product, Long> {

    fun findAllByOrderById(): List<Product>
    fun findAllByProductType(productType: ProductType): List<Product>
    fun findAllByProductTypeAndStateInAndPriceLessThanEqual(productType: ProductType, listState: List<ProductState>, price: Float): List<Product>
    fun deleteAllByProductType(productType: ProductType)

    @Query(nativeQuery = true, value = "select max(price) from products")
    fun maxPrice(): Float

    @Query(nativeQuery = true, value = "select min(price) from products")
    fun minPrice(): Float
}
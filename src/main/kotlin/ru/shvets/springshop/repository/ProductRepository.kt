package ru.shvets.springshop.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.shvets.springshop.entity.ProductEntity
import ru.shvets.springshop.model.ProductState
import ru.shvets.springshop.entity.ProductTypeEntity

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  30.09.2022 20:06
 */

@Repository
interface ProductRepository: JpaRepository<ProductEntity, Long> {

    fun findAllByOrderById(): List<ProductEntity>
    fun findAllByProductType(productType: ProductTypeEntity): List<ProductEntity>
    fun findAllByProductTypeAndStateInAndPriceLessThanEqual(productType: ProductTypeEntity, listState: List<ProductState>, price: Int): List<ProductEntity>
    fun deleteAllByProductType(productType: ProductTypeEntity)
    @Query(nativeQuery = true, value = "select max(price) from products")
    fun maxPrice(): Float
    @Query(nativeQuery = true, value = "select min(price) from products")
    fun minPrice(): Float
}
package ru.shvets.springshop.repository;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.shvets.springshop.entity.ProductType

@Repository
interface ProductTypeRepository : JpaRepository<ProductType, Long> {

    fun findAllByOrderByOrderIdAsc(): List<ProductType>
    fun findAllByIdIn(list: List<Long>): List<ProductType>

    @Query(nativeQuery = true, value = "select max(order_id) from product_type")
    fun maxAllByOrderId(): Int
}
package ru.shvets.springshop.repository;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.shvets.springshop.entity.ProductTypeEntity

@Repository
interface ProductTypeRepository : JpaRepository<ProductTypeEntity, Long> {

    fun findAllByOrderByOrderIdAsc(): List<ProductTypeEntity>
    fun findAllByIdIn(list: List<Long>): List<ProductTypeEntity>

    @Query(nativeQuery = true, value = "select max(order_id) from product_type")
    fun maxAllByOrderId(): Int
}
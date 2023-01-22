package ru.shvets.springshop.repository;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import ru.shvets.springshop.entity.OrderEntity

@Repository
interface OrderEntityRepository : JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {
}
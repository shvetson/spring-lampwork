package ru.shvets.springshop.service

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.ResponseStatus
import ru.shvets.springshop.controller.DefaultController
import ru.shvets.springshop.entity.Product
import ru.shvets.springshop.entity.ProductType
import ru.shvets.springshop.repository.ProductRepository
import ru.shvets.springshop.repository.ProductTypeRepository

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  03.10.2022 14:31
 */

@Service
class ProductTypeService(
    private val productTypeRepository: ProductTypeRepository,
    private val productRepository: ProductRepository,
) {
    fun getAll(): List<ProductType> {
        log.info("Find all types")
        return productTypeRepository.findAll()
    }

    fun getAllCustom(listTypesId: List<Long>): List<ProductType> {
        return productTypeRepository.findAllByIdIn(listTypesId)
    }

    fun getById(id: Long): ProductType {
        val type = productTypeRepository.findById(id).orElse(null) ?: throw TypeNotFoundException(id)
        log.info("Selected type with id=$id - ${type.name}")
        return type
    }

    fun getAllByOrder(): List<ProductType> {
        log.info("Find all types")
        return productTypeRepository.findAllByOrderByOrderIdAsc()
    }

    fun save(productType: ProductType) {
        productTypeRepository.save(productType)
        log.info("Added a new type")
    }

    fun create(): ProductType {
        val maxOrderId = productTypeRepository.maxAllByOrderId()
        return ProductType(
            id = 0L,
            name = "",
            orderId = maxOrderId + 1
        )
    }

    @Transactional
    fun delete(id: Long) {
        val productType = productTypeRepository.findById(id).orElse(null) ?: throw TypeNotFoundException(id)
        productRepository.deleteAllByProductType(productType)
        productTypeRepository.delete(productType)
        log.info("Deleted all products by type with id=$id")
    }

    companion object {
        private val log = LoggerFactory.getLogger(DefaultController::class.java)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class TypeNotFoundException(id: Long) : RuntimeException("Type with id=$id not found")
}
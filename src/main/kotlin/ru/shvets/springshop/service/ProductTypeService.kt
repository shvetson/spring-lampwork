package ru.shvets.springshop.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.shvets.springshop.entity.ProductType
import ru.shvets.springshop.exception.TypeNotFoundException
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
    private val logger = KotlinLogging.logger {}

    fun getAll(): List<ProductType> {
        logger.info("Find all types")
        return productTypeRepository.findAll()
    }

    fun getAllCustom(listTypesId: List<Long>): List<ProductType> {
        return productTypeRepository.findAllByIdIn(listTypesId)
    }

    fun getById(id: Long): ProductType {
        val type = productTypeRepository.findById(id).orElse(null) ?: throw TypeNotFoundException(id)
        logger.info("Selected type with id=$id - ${type.name}")
        return type
    }

    fun getAllByOrder(): List<ProductType> {
        logger.info("Find all types")
        return productTypeRepository.findAllByOrderByOrderIdAsc()
    }

    fun save(productType: ProductType) {
        productTypeRepository.save(productType)
        logger.info("Added a new type")
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
        logger.info("Deleted all products by type with id=$id")
    }
}
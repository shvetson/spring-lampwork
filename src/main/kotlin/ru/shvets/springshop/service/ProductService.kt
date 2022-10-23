package ru.shvets.springshop.service

import com.fasterxml.jackson.databind.deser.DataFormatReaders
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import ru.shvets.springshop.dto.ProductDto
import ru.shvets.springshop.controller.DefaultController
import ru.shvets.springshop.entity.Product
import ru.shvets.springshop.entity.ProductState
import ru.shvets.springshop.entity.ProductType
import ru.shvets.springshop.repository.ProductRepository
import ru.shvets.springshop.repository.ProductTypeRepository
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.floor
import kotlin.math.roundToInt

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  03.10.2022 15:40
 */

@Service
class ProductService(
    private val productTypeRepository: ProductTypeRepository,
    private val productRepository: ProductRepository
) {
    fun getAll(): List<ProductDto> {
        log.info("Find all products")
        return productRepository.findAllByOrderById().map {
            it.toDto()
        }
    }

    fun getAllByMap(): LinkedHashMap<ProductType, List<ProductDto>> {
        val listTypes = productTypeRepository.findAllByOrderByOrderIdAsc()
        val mapProductAndType: LinkedHashMap<ProductType, List<ProductDto>> = LinkedHashMap()

        listTypes.forEach { type ->
            mapProductAndType[type] = productRepository.findAllByProductType(type).map {
                it.toDto()
            }
        }
        log.info("Find all products by types")
        return mapProductAndType
    }

    fun getAllByFilter(
        listTypesId: List<Long>,
        availableProducts: Boolean,
        saleProducts: Boolean,
        newProducts: Boolean,
        priceRangeProducts: Long
    ): LinkedHashMap<ProductType, List<ProductDto>> {
        val listTypes = productTypeRepository.findAllByIdIn(listTypesId)

        val listState = mutableListOf<ProductState>()
        for (state in ProductState.values()) {
            listState.add(state)
        }

        if (availableProducts) {
            listState.remove(ProductState.SOLD)
        }

        if (!saleProducts) {
            listState.remove(ProductState.SALE)
        }

        if (!newProducts) {
            listState.remove(ProductState.NEW)
        }

        val mapProductAndType: LinkedHashMap<ProductType, List<ProductDto>> = LinkedHashMap()

        listTypes.forEach { type ->
            mapProductAndType[type] = productRepository.findAllByProductTypeAndStateInAndPriceLessThanEqual(type, listState, priceRangeProducts.toFloat()).map {
                it.toDto()
            }
        }
        log.info("Find all products by types")
        return mapProductAndType
    }

    fun getById(id: Long): Product {
        val product = productRepository.findById(id).orElse(null) ?: throw ProductNotFoundException(id)
        log.info("Selected product with id=$id - ${product.name}")
        return product
    }

    fun create(): Product {
        val types: List<ProductType> = productTypeRepository.findAll()
        var indexNewType = types.indexOfFirst { it.name.uppercase() == "NEW" }
        if (indexNewType == -1) indexNewType = 0

        return Product(
            id = 0L,
            name = "",
            price = 0F,
            oldPrice = 0F,
            image = "",
            description = "",
            productType = types[indexNewType]
        )
    }

    fun save(product: Product) {
        productRepository.save(product)
        log.info("Added a new product")
    }

    fun delete(id: Long) {
        val product = productRepository.findById(id).orElse(null) ?: throw ProductNotFoundException(id)
        productRepository.delete(product)
        log.info("Deleted product with id=$id")
    }

    fun maxPrice(): Int {
        return productRepository.maxPrice().roundToInt()
    }

    fun minPrice(): Int {
        return productRepository.minPrice().roundToInt()
    }

    fun Long.toDate(): String = SimpleDateFormat("dd/MM/yyyy").format(Date(this))

    fun Product.toDto(): ProductDto {
        return ProductDto(
            id = this.id,
            name = this.name,
            created = this.created.toDate(),
            price = this.price,
            oldPrice = this.oldPrice,
            state = this.state,
            sold = this.sold.toDate(),
            image = this.image,
            description = this.description,
            productType = this.productType
        )
    }

    companion object {
        private val log = LoggerFactory.getLogger(DefaultController::class.java)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class ProductNotFoundException(id: Long) : RuntimeException("Product with id=$id not found")
}
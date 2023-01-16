package ru.shvets.springshop.service

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.shvets.springshop.api.response.ProductsResponse
import ru.shvets.springshop.api.response.ProductsResponse.Companion.toResponse
import ru.shvets.springshop.dto.ProductDto
import ru.shvets.springshop.dto.ProductDto.Companion.toDto
import ru.shvets.springshop.entity.Product
import ru.shvets.springshop.entity.ProductState
import ru.shvets.springshop.entity.ProductType
import ru.shvets.springshop.exception.ProductNotFoundException
import ru.shvets.springshop.repository.ProductRepository
import ru.shvets.springshop.repository.ProductTypeRepository
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
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
    private val logger = KotlinLogging.logger {}

    @Value("\${upload.path}")
    lateinit var uploadPath: String

    fun getAll(): List<ProductDto> {
        logger.info("Сформирован список всех продуктов.")
        return productRepository.findAllByOrderById().map {
            it.toDto()
        }
    }

    fun getAllForApi(): List<ProductsResponse> {
        logger.info("Список всех товаров выгружен через API.")
        return getAll().map {
            it.toResponse()
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
        logger.info("Сформирован список всех продуктов в разрезе по видам.")
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
            mapProductAndType[type] = productRepository.findAllByProductTypeAndStateInAndPriceLessThanEqual(
                type,
                listState,
                priceRangeProducts.toInt()
            ).map {
                it.toDto()
            }
        }
        logger.info("Сформирован список всех продуктов по фильтру.")
        return mapProductAndType
    }

    fun getById(id: Long): Product {
        val product = productRepository.findById(id).orElse(null) ?: throw ProductNotFoundException(id)
        logger.info("Выбран товар ${product.name}")
        return product
    }

    fun create(): Product {
        val types: List<ProductType> = productTypeRepository.findAll()
        var indexNewType = types.indexOfFirst { it.name.uppercase() == "NEW" }
        if (indexNewType == -1) indexNewType = 0

        return Product(
            id = 0L,
            name = "",
            price = 0,
            oldPrice = 0,
            image = "",
            description = "",
            productType = types[indexNewType]
        )
    }

    fun save(product: Product) {
        productRepository.save(product)
        logger.info("Добавлен новый || обновлен продукт - ${product.name}.")
    }

    fun delete(id: Long) {
        val product = productRepository.findById(id).orElse(null) ?: throw ProductNotFoundException(id)
        productRepository.delete(product)
        logger.info("Данные о продукте удалены.")
    }

    fun deleteFileByProductId(id: Long): Boolean {
        val product = getById(id)
        val filename = product.image
        val path = Paths.get("$uploadPath/$filename")

        return try {
            val result = Files.deleteIfExists(path)
            if (result) {
                logger.info("Файл картинки продукта удален.")
                true
            } else {
                logger.error("Файл картинки продукта не удалось удалить.")
                false
            }
        } catch (e: IOException) {
            logger.error("Файл картинки продукта не удалось удалить.")
            e.printStackTrace()
            false
        }
    }

    fun transferFile(file: MultipartFile): String {
        val uploadDir = File(uploadPath)

        if (!File(uploadPath).exists()) {
            logger.error("Директория для хранения файлов (по умолчанию) не существует.")
            uploadDir.mkdir()
        }

        val uuidFile = UUID.randomUUID().toString().replace("-", "").substring(0, 10)
        val resultFileName = uuidFile + "." + getFileExtension(file)
        file.transferTo(File("$uploadPath/$resultFileName"))

        return resultFileName
    }

    fun maxPrice(): Int {
        return productRepository.maxPrice().roundToInt()
    }

    fun minPrice(): Int {
        return productRepository.minPrice().roundToInt()
    }

    fun getFileExtension(file: MultipartFile): String {
        val fileName = file.originalFilename

        return if (fileName?.lastIndexOf(".") != -1 && fileName?.lastIndexOf(".") != 0) {
            fileName?.substring(fileName.lastIndexOf(".") + 1).toString()
        } else {
            ""
        }
    }
}
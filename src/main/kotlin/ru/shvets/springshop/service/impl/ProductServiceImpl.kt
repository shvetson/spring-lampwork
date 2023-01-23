package ru.shvets.springshop.service.impl

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.shvets.springshop.api.response.ProductsResponse
import ru.shvets.springshop.api.response.ProductsResponse.Companion.toResponse
import ru.shvets.springshop.dto.ProductDto
import ru.shvets.springshop.dto.ProductDto.Companion.toDto
import ru.shvets.springshop.entity.ProductEntity
import ru.shvets.springshop.model.ProductState
import ru.shvets.springshop.entity.ProductTypeEntity
import ru.shvets.springshop.exception.ProductNotFoundException
import ru.shvets.springshop.repository.ProductRepository
import ru.shvets.springshop.repository.ProductTypeRepository
import ru.shvets.springshop.service.ProductService
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
class ProductServiceImpl(
    private val productTypeRepository: ProductTypeRepository,
    private val productRepository: ProductRepository
) : ProductService {
    private val logger = KotlinLogging.logger {}

    @Value("\${upload.path}")
    lateinit var uploadPath: String

    override fun getAll(): List<ProductDto> {
        logger.info("Сформирован список всех продуктов.")
        return productRepository.findAllByOrderById().map {
            it.toDto()
        }
    }

    override fun getAllApi(): List<ProductsResponse> {
        logger.info("Список всех товаров выгружен через API.")
        return getAll().map {
            it.toResponse()
        }
    }

    override fun getAllByMap(): LinkedHashMap<ProductTypeEntity, List<ProductDto>> {
        val listTypes = productTypeRepository.findAllByOrderByOrderIdAsc()
        val mapProductAndType: LinkedHashMap<ProductTypeEntity, List<ProductDto>> = LinkedHashMap()

        listTypes.forEach { type ->
            mapProductAndType[type] = productRepository.findAllByProductType(type).map {
                it.toDto()
            }
        }
        logger.info("Сформирован список всех продуктов в разрезе по видам.")
        return mapProductAndType
    }

    override fun getAllByFilter(
        listTypesId: List<Long>,
        availableProducts: Boolean,
        saleProducts: Boolean,
        newProducts: Boolean,
        priceRangeProducts: Long
    ): LinkedHashMap<ProductTypeEntity, List<ProductDto>> {
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

        val mapProductAndType: LinkedHashMap<ProductTypeEntity, List<ProductDto>> = LinkedHashMap()

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

    override fun getById(id: Long): ProductEntity {
        val product = productRepository.findById(id).orElse(null) ?: throw ProductNotFoundException(id)
        logger.info("Выбран товар ${product.name}")
        return product
    }

    override fun create(): ProductEntity {
        return ProductEntity()
    }

    override fun save(product: ProductEntity) {
        productRepository.save(product)
        logger.info("Добавлен новый || обновлен продукт - ${product.name}.")
    }

    override fun delete(id: Long) {
        val product = productRepository.findById(id).orElse(null) ?: throw ProductNotFoundException(id)
        productRepository.delete(product)
        logger.info("Данные о продукте удалены.")
    }

    override fun deleteFileByProductId(id: Long): Boolean {
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

    override fun transferFile(file: MultipartFile): String {
        val uploadDir = File(uploadPath)

        if (!File(uploadPath).exists()) {
            logger.error("Директория для хранения файлов (по умолчанию) не существует.")
            uploadDir.mkdir()
        }

        val uuidFile = UUID.randomUUID().toString().replace("-", "").substring(0, 15)
        val resultFileName = uuidFile + "." + getFileExtension(file)
        file.transferTo(File("$uploadPath/$resultFileName"))

        return resultFileName
    }

    override fun maxPrice(): Int {
        return productRepository.maxPrice().roundToInt()
    }

    override fun minPrice(): Int {
        return productRepository.minPrice().roundToInt()
    }

    private fun getFileExtension(file: MultipartFile): String {
        val fileName = file.originalFilename

        return if (fileName?.lastIndexOf(".") != -1 && fileName?.lastIndexOf(".") != 0) {
            fileName?.substring(fileName.lastIndexOf(".") + 1).toString()
        } else {
            ""
        }
    }
}
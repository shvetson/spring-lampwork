package ru.shvets.springshop.controller

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.shvets.springshop.api.response.InitResponse
import ru.shvets.springshop.api.response.ProductsResponse
import ru.shvets.springshop.api.response.SettingsResponse
import ru.shvets.springshop.service.ProductService
import ru.shvets.springshop.service.SettingsService

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  31.12.2022 00:27
 */

@RestController
@RequestMapping("/api/lampwork", produces = [MediaType.APPLICATION_JSON_VALUE])
class ApiController(
    private val initResponse: InitResponse,
    private val settingsService: SettingsService,
    private val productService: ProductService
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/inits")
    fun init(): InitResponse {
        logger.info("Load inits data via API")
        return initResponse
    }

    @GetMapping("/settings")
    fun settings(): ResponseEntity<SettingsResponse> {
        logger.info("Load settings via API")
        return ResponseEntity(settingsService.getGlobalSettings(), HttpStatus.OK)
    }

    @GetMapping("/products")
    fun getAllProducts(): ResponseEntity<List<ProductsResponse>> {
        logger.info("Load products data via API")
        return ResponseEntity(productService.getAllApi(), HttpStatus.OK )
    }
}
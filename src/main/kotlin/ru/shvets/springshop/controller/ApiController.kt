package ru.shvets.springshop.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.shvets.springshop.api.response.InitResponse
import ru.shvets.springshop.api.response.SettingsResponse
import ru.shvets.springshop.service.SettingsService

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  31.12.2022 00:27
 */

@RestController
@RequestMapping("/api")
class ApiController(
    val initResponse: InitResponse,
    val settingsService: SettingsService
) {

    @GetMapping("/init")
    fun init(): InitResponse {
        return initResponse
    }

    @GetMapping("/settings")
    fun settings(): SettingsResponse {
        return settingsService.getGlobalSettings()
    }

    @GetMapping("/products")
    fun getAllProducts() {

    }
}
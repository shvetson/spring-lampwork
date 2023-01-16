package ru.shvets.springshop.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  12.01.2023 23:31
 */

@Configuration
class AppConfig: WebMvcConfigurer {

    @Value("\${upload.path}")
    lateinit var uploadPath: String

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        super.addResourceHandlers(registry)

        registry.addResourceHandler("/img/**")
            .addResourceLocations("file://$uploadPath/")
    }
}
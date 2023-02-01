package ru.shvets.springshop.mapper

import ru.shvets.springshop.dto.ProductDto
import ru.shvets.springshop.entity.ProductEntity
import ru.shvets.springshop.mapper.ClientMapper.Companion.toDto
import ru.shvets.springshop.mapper.ClientMapper.Companion.toEntity

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  28.01.2023 20:21
 */

class ProductMapper {
    companion object {
        fun ProductEntity.toDto(): ProductDto {
            return ProductDto(
                id = this.id,
                name = this.name,
                created = this.created,
                price = this.price,
                oldPrice = this.oldPrice,
                state = this.state,
                sold = this.sold,
                image = this.image,
                description = this.description,
                productType = this.productType,
                client = this.client?.toDto(),
                enabled = this.enabled
            )
        }

        fun ProductDto.toEntity(): ProductEntity {
            return ProductEntity(
                id = this.id,
                name = this.name,
                created = this.created,
                price = this.price,
                oldPrice = this.oldPrice,
                state = this.state,
                sold = this.sold,
                image = this.image,
                description = this.description,
                productType = this.productType,
                client = this.client?.toEntity(),
                enabled = this.enabled
            )
        }
    }
}
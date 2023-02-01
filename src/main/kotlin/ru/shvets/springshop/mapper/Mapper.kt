package ru.shvets.springshop.mapper

import org.springframework.stereotype.Component

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  28.01.2023 20:18
 */

interface Mapper<D, E> {
   fun toDto(entity: E): D
   fun toEntity(dto: D): E
}
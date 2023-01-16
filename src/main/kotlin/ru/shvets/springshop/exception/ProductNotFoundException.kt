package ru.shvets.springshop.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  12.01.2023 13:29
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
class ProductNotFoundException(id: Long): RuntimeException("Product with id=$id not found")
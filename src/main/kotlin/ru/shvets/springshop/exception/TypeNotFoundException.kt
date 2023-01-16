package ru.shvets.springshop.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  14.01.2023 17:15
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
class TypeNotFoundException(id: Long) : RuntimeException("Type with id=$id not found")
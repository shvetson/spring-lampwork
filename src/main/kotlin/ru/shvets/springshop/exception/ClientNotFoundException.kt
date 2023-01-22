package ru.shvets.springshop.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  18.01.2023 00:20
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
class ClientNotFoundException(id: Long): RuntimeException("Клиент с id=$id не найден")


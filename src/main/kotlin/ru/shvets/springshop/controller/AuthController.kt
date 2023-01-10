package ru.shvets.springshop.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  31.12.2022 00:23
 */

@RestController
@RequestMapping("/auth")
class AuthController {

    @PostMapping("/login")
    fun login() {

    }
}
package ru.shvets.springshop.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  22.01.2023 11:39
 */

@Controller
@RequestMapping("/api/v1/orders")
class OrdersController {

    @GetMapping
    fun main(model: Model): String {
        return "/orders/orders"
    }
}
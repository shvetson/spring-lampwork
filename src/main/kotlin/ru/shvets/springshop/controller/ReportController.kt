package ru.shvets.springshop.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  20.01.2023 17:25
 */

@Controller
@RequestMapping("/api/v1/reports")
class ReportController {

    @GetMapping
    fun main(model: Model): String {
        return "/reports/reports"
    }
}
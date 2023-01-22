package ru.shvets.springshop.controller

import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import ru.shvets.springshop.dto.AddressDto
import ru.shvets.springshop.dto.ClientDto
import ru.shvets.springshop.dto.ProductDto
import ru.shvets.springshop.service.ClientService
import java.util.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  31.12.2022 00:07
 */

@Controller
@RequestMapping("/admin")
class ClientController(
    private val clientService: ClientService
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/clients")
    fun showClientsList(model: Model): String {
        model["clients"] = clientService.getAllClients()
        return "clientsList"
    }

    @GetMapping("/clients/add")
    fun addClient(model: Model): String {
        val client = clientService.createClient()
        model["client"] = client
        model["flagEdit"] = false
        return "clientForm"
    }

    @PostMapping("/clients/add")
    fun addClient(@ModelAttribute("client") client: ClientDto): String {
        clientService.saveClient(client)
        return "redirect:/admin/clients"
    }

    @GetMapping("/clients/edit/{id}")
    fun editClient(@PathVariable("id") id: Long, model: Model): String {
        val shoppingList: List<ProductDto> = emptyList()
        val total = shoppingList.map(ProductDto::price).sumOf { it?.toLong() ?: 0 }
        val client = clientService.getClientById(id)
        model["client"] = client
        model["shopping"] = shoppingList
        model["total"] = total
        model["flagEdit"] = true
        return "clientForm"
    }

    @GetMapping("/clients/delete/{id}")
    fun deleteClient(@PathVariable("id") id: Long): String {
        clientService.deleteClient(id)
        return "redirect:/admin/clients"
    }
}
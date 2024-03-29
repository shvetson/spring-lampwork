package ru.shvets.springshop.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import ru.shvets.springshop.service.ProductService
import ru.shvets.springshop.service.ProductTypeService

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  26.09.2022 17:21
 */

@Controller
@RequestMapping("/api/v1")
class DefaultController(
    private val productTypeService: ProductTypeService,
    private val productService: ProductService
) {
    @GetMapping("/home")
    fun home(model: Model): String {
        return "index"
    }

    @GetMapping("/about")
    fun about(model: Model): String {
        return "about"
    }

    @GetMapping("/contacts")
    fun contacts(model: Model): String {
        return "contacts"
    }

    @GetMapping("/gallery")
    fun showAllProducts(model: Model): String {
        model["map"] = productService.getAllByMap()
        model["types"] = productTypeService.getAll()
        model["minPrice"] = productService.minPrice()
        model["maxPrice"] = productService.maxPrice()
        return "/products/products"
    }

    @PostMapping("/gallery/filter")
    fun showAllProductsCustom(
        @RequestParam("checkedTypesList") checkedTypesList: List<Long>,
        @RequestParam("availableProducts") availableProducts: Boolean = false,
        @RequestParam("saleProducts") saleProducts: Boolean = true,
        @RequestParam("newProducts") newProducts: Boolean = true,
        @RequestParam("priceRangeProducts") priceRangeProducts: Long,
        model: Model
    ): String {
        model["map"] = productService.getAllByFilter(checkedTypesList, availableProducts, saleProducts, newProducts, priceRangeProducts)
        model["types"] = productTypeService.getAll()
        model["minPrice"] = productService.minPrice()
        model["maxPrice"] = productService.maxPrice()

        model["typesCustom"] = checkedTypesList
        model["availableProd"] = availableProducts
        model["saleProd"] = saleProducts
        model["newProd"] = newProducts
        model["priceProd"] = priceRangeProducts
        return "/products/products"
    }

    @GetMapping("/products/{id}")
    fun products(@PathVariable("id") id: Long, model: Model): String {
        model["product"] = productService.getProductById(id)
        return "/products/product"
    }
}
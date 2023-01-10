package ru.shvets.springshop.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import ru.shvets.springshop.entity.Product
import ru.shvets.springshop.entity.ProductType
import ru.shvets.springshop.service.ProductService
import ru.shvets.springshop.service.ProductTypeService

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  31.12.2022 00:07
 */

@Controller
@RequestMapping("/api/v1/admin")
class AdminController(
    private val productTypeService: ProductTypeService,
    private val productService: ProductService
) {

    @GetMapping(*["", "/products"])
    fun productsList(model: Model): String {
        model["products"] = productService.getAll()
        return "productsList"
    }

    @PostMapping("/products/add")
    fun productListAddSubmit(product: Product): String {
        productService.save(product)
        return "redirect:/api/v1/admin/products"
    }

    @GetMapping("/products/add")
    fun productListAdd(model: Model): String {
        model["types"] = productTypeService.getAll()
        model["product"] = productService.create()
        model["flagEdit"] = false
        return "productForm"
    }

    @GetMapping("/products/delete/{id}")
    fun productListDelete(@PathVariable("id") id: Long): String {
        productService.delete(id)
        return "redirect:/api/v1/admin/products"
    }

    @GetMapping("/products/edit/{id}")
    fun productListEdit(@PathVariable("id") id: Long, model: Model): String {
        model["types"] = productTypeService.getAll()
        model["product"] = productService.getById(id)
        model["flagEdit"] = true
        return "productForm"
    }

    @GetMapping("/types")
    fun productTypeList(model: Model): String {
        model["types"] = productTypeService.getAll()
        return "typesList"
    }

    @PostMapping("/types/add")
    fun productTypeListAddSubmit(productType: ProductType): String {
        productTypeService.save(productType)
        return "redirect:/api/v1/admin/types"
    }

    @GetMapping("/types/add")
    fun productTypeListAdd(model: Model): String {
        model["productType"] = productTypeService.create()
        return "typeForm"
    }

    @GetMapping("/types/delete/{id}")
    fun productTypeListDelete(@PathVariable("id") id: Long): String {
        productTypeService.delete(id)
        return "redirect:/api/v1/admin/types"
    }

    @GetMapping("/types/edit/{id}")
    fun productTypeListEdit(@PathVariable("id") id: Long, model: Model): String {
        model["productType"] = productTypeService.getById(id)
        return "typeForm"
    }

}
package ru.shvets.springshop.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.shvets.springshop.entity.Product
import ru.shvets.springshop.entity.ProductType
import ru.shvets.springshop.model.User
import ru.shvets.springshop.service.ProductService
import ru.shvets.springshop.service.ProductTypeService
import java.util.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  31.12.2022 00:07
 */

@Controller
@RequestMapping("/admin")
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
    fun productListAddSubmit(
        @ModelAttribute product: Product,
        @RequestParam(name = "file", required = false) file: MultipartFile
    ): String {
        if (!file.isEmpty) {
            if (product.id != 0L && product.image != file.originalFilename) {
                println("Разные файлы")
                productService.deleteFileByProductId(product.id)
            }
            product.image = productService.transferFile(file)
        }

        productService.save(product)
        return "redirect:/admin/products"
//        return "productForm"
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
        if (productService.deleteFileByProductId(id)) {
            productService.delete(id)
        }
        return "redirect:/admin/products"
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
        return "redirect:/admin/types"
    }

    @GetMapping("/types/add")
    fun productTypeListAdd(model: Model): String {
        model["productType"] = productTypeService.create()
        return "typeForm"
    }

    @GetMapping("/types/delete/{id}")
    fun productTypeListDelete(@PathVariable("id") id: Long): String {
        productTypeService.delete(id)
        return "redirect:/admin/types"
    }

    @GetMapping("/types/edit/{id}")
    fun productTypeListEdit(@PathVariable("id") id: Long, model: Model): String {
        model["productType"] = productTypeService.getById(id)
        return "typeForm"
    }

    @GetMapping("/users")
    fun getAllUsers(): List<User> {
        return emptyList()
    }
}
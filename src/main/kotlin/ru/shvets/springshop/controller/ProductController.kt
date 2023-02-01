package ru.shvets.springshop.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.shvets.springshop.dto.ProductDto
import ru.shvets.springshop.entity.ProductTypeEntity
import ru.shvets.springshop.service.ClientService
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
class ProductController(
    private val productTypeService: ProductTypeService,
    private val productService: ProductService,
    private val clientService: ClientService
) {
    @GetMapping(*["", "/products"])
    fun showProductsList(model: Model): String {
        model["products"] = productService.getAll()
        return "/products/productsList"
    }

    @PostMapping("/products/update")
    fun addProduct(
        @ModelAttribute product: ProductDto,
        @RequestParam(name = "file", required = false) file: MultipartFile
    ): String {
        if (!file.isEmpty) {
            if (product.id != 0L && product.image != file.originalFilename) {
                product.id?.let { productService.deleteFileByProductId(it) }
            }
            product.image = productService.transferFile(file)
        }
        productService.save(product)
        return "redirect:/admin/products"
    }

    @GetMapping("/products/add")
    fun addProduct(model: Model): String {
        model["types"] = productTypeService.getAll()
        model["product"] = productService.create()
        model["flagEdit"] = false
        return "/products/productForm"
    }

    @GetMapping("/products/delete/{id}")
    fun deleteProduct(@PathVariable("id") id: Long): String {
        if (productService.deleteFileByProductId(id)) {
            productService.delete(id)
        }
        return "redirect:/admin/products"
    }

    @GetMapping("/products/edit/{id}")
    fun editProduct(@PathVariable("id") id: Long, model: Model): String {
        model["types"] = productTypeService.getAll()
        model["product"] = productService.getProductById(id)
        model["clients"] = clientService.getAllClients()
        model["flagEdit"] = true
        return "/products/productForm"
    }

    //Types

    @GetMapping("/types")
    fun showProductTypesList(model: Model): String {
        model["types"] = productTypeService.getAll()
        return "/products/typesList"
    }

    @PostMapping("/types/add")
    fun addProductType(productType: ProductTypeEntity): String {
        productTypeService.save(productType)
        return "redirect:/admin/types"
    }

    @GetMapping("/types/add")
    fun addProductType(model: Model): String {
        model["productType"] = productTypeService.create()
        return "/products/typeForm"
    }

    @GetMapping("/types/delete/{id}")
    fun deleteProductType(@PathVariable("id") id: Long): String {
        productTypeService.delete(id)
        return "redirect:/admin/types"
    }

    @GetMapping("/types/edit/{id}")
    fun editProductType(@PathVariable("id") id: Long, model: Model): String {
        model["productType"] = productTypeService.getById(id)
        return "/products/typeForm"
    }
}
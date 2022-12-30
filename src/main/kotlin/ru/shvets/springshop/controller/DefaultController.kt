package ru.shvets.springshop.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.shvets.springshop.entity.Product
import ru.shvets.springshop.entity.ProductType
import ru.shvets.springshop.service.ProductService
import ru.shvets.springshop.service.ProductTypeService

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  26.09.2022 17:21
 */

@Controller
//@RequestMapping("/api/v1")
class DefaultController(
    private val productTypeService: ProductTypeService,
    private val productService: ProductService
) {
    @GetMapping("/index")
    fun home(model: Model): String {
        return "index"
    }

    @GetMapping("/gallery")
    fun showAllProducts(model: Model): String {
        model["map"] = productService.getAllByMap()
        model["types"] = productTypeService.getAll()
        model["minPrice"] = productService.minPrice()
        model["maxPrice"] = productService.maxPrice()
        return "products"
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
        return "products"
    }

    @GetMapping("/products/{id}")
    fun products(@PathVariable("id") id: Long, model: Model): String {
        model["product"] = productService.getById(id)
        return "product"
    }

    @GetMapping(*["/admin", "/admin/products"])
    fun productsList(model: Model): String {
        model["products"] = productService.getAll()
        return "productsList"
    }

    @PostMapping("/admin/products/add")
    fun productListAddSubmit(product: Product): String {
        productService.save(product)
        return "redirect:/admin/products"
    }

    @GetMapping("/admin/products/add")
    fun productListAdd(model: Model): String {
        model["types"] = productTypeService.getAll()
        model["product"] = productService.create()
        model["flagEdit"] = false
        return "productForm"
    }

    @GetMapping("/admin/products/delete/{id}")
    fun productListDelete(@PathVariable("id") id: Long): String {
        productService.delete(id)
        return "redirect:/admin/products"
    }

    @GetMapping("/admin/products/edit/{id}")
    fun productListEdit(@PathVariable("id") id: Long, model: Model): String {
        model["types"] = productTypeService.getAll()
        model["product"] = productService.getById(id)
        model["flagEdit"] = true
        return "productForm"
    }

    @GetMapping("/admin/types")
    fun productTypeList(model: Model): String {
        model["types"] = productTypeService.getAll()
        return "typesList"
    }

    @PostMapping("/admin/types/add")
    fun productTypeListAddSubmit(productType: ProductType): String {
        productTypeService.save(productType)
        return "redirect:/admin/types"
    }

    @GetMapping("/admin/types/add")
    fun productTypeListAdd(model: Model): String {
        model["productType"] = productTypeService.create()
        return "typeForm"
    }

    @GetMapping("/admin/types/delete/{id}")
    fun productTypeListDelete(@PathVariable("id") id: Long): String {
        productTypeService.delete(id)
        return "redirect:/admin/types"
    }

    @GetMapping("/admin/types/edit/{id}")
    fun productTypeListEdit(@PathVariable("id") id: Long, model: Model): String {
        model["productType"] = productTypeService.getById(id)
        return "typeForm"
    }
}
package ru.shvets.springshop.entity

import javax.persistence.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  26.09.2022 14:14
 */

@Entity
@Table(name = "product_type")
class ProductType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "order_id")
    val orderId: Int,

    @OneToMany(mappedBy = "productType")
    val products: List<Product> = emptyList()
) {
    override fun toString(): String {
        return name
    }
}
package ru.shvets.springshop.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.Hibernate
import javax.persistence.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  26.09.2022 14:14
 */

@Entity
@Table(name = "product_type")
class ProductTypeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "order_id", nullable = false)
    var orderId: Int? = null

    @OneToMany(mappedBy = "productType", fetch = FetchType.EAGER)
    @JsonBackReference
    var products: MutableList<ProductEntity> = mutableListOf()

    constructor()
    constructor(id: Long?, name: String?, orderId: Int?, products: MutableList<ProductEntity>) {
        this.id = id
        this.name = name
        this.orderId = orderId
        this.products = products
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ProductTypeEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id, name = $name)"
    }
}
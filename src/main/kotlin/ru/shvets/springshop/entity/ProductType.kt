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
data class ProductType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "order_id")
    val orderId: Int,

    @OneToMany(mappedBy = "productType", fetch = FetchType.EAGER)
    @JsonBackReference
    val products: MutableList<Product> = mutableListOf()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ProductType

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id, name = $name  )"
    }
}
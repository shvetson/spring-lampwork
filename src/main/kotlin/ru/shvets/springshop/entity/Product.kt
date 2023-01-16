package ru.shvets.springshop.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.Hibernate
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  26.09.2022 14:24
 */

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "date_created", updatable = false)
    val created: Long = Date().time,
    @Column(name = "price")
    val price: Int,
    @Column(name = "old_price")
    var oldPrice: Int = 0,
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    var state: ProductState = ProductState.NEW,
    @Column(name = "date_sold")
    var sold: Long = 0L,
    @Column(name = "image")
    var image: String,
    @Type(type = "text")
    @Column(name = "description")
    val description: String,

    @ManyToOne(optional = false, cascade = [CascadeType.ALL])
    @JoinColumn(name = "product_type_id")
    @JsonManagedReference
    val productType: ProductType
) {

//    @PrePersist
//    fun onCreate() {
//        created = Date().time
//    }

    @PreUpdate
    fun onUpdate() {
        if (state == ProductState.SOLD) {
            sold = Date().time
            oldPrice = 0
        } else {
            sold = 0L
        }

        if (oldPrice != 0) {
            state = ProductState.SALE
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Product

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id, name = $name )"
    }
}
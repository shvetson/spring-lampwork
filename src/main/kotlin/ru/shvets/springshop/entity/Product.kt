package ru.shvets.springshop.entity

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
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "date_created")
    var created: Long = Date().time,
    @Column(name = "price")
    val price: Float,
    @Column(name = "old_price")
    val oldPrice: Float = 0F,
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    val state: ProductState = ProductState.NEW,
    @Column(name = "date_sold")
    var sold: Long = 0L,
    @Column(name = "image")
    val image: String,
    @Type(type = "text")
    @Column(name = "description")
    val description: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    val productType: ProductType

//    @OneToOne(cascade = [CascadeType.ALL])
//    @JoinColumn(name = "product_type_id")
//    val productType: ProductType
) {
    override fun toString(): String {
        return name
    }

//    @PrePersist
//    fun onCreate() {
//        created = Date().time
//    }

    @PreUpdate
    fun onUpdate() {
        sold = if (state == ProductState.SOLD) Date().time else 0L
    }
}
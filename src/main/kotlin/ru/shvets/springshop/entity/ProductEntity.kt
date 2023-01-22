package ru.shvets.springshop.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.Hibernate
import org.hibernate.annotations.Type
import ru.shvets.springshop.model.ProductState
import java.util.*
import javax.persistence.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  26.09.2022 14:24
 */

@Entity
@Table(name = "products")
class ProductEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "date_created", updatable = false, nullable = false)
    var created: Long? = Date().time

    @Column(name = "price", nullable = false)
    var price: Int? = null

    @Column(name = "old_price", nullable = false)
    var oldPrice: Int? = 0

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    var state: ProductState = ProductState.NEW

    @Column(name = "date_sold", nullable = false)
    var sold: Long? = 0L

    @Column(name = "image", nullable = false)
    var image: String? = null

    @Type(type = "text")
    @Column(name = "description", nullable = false)
    var description: String? = null

    @ManyToOne(optional = false, cascade = [CascadeType.ALL])
    @JoinColumn(name = "product_type_id", nullable = false)
    @JsonManagedReference
    var productType: ProductTypeEntity = ProductTypeEntity()

    @ManyToOne(optional = true, cascade = [CascadeType.ALL])
    @JoinColumn(name = "client_id", nullable = false)
    var client: ClientEntity? = ClientEntity()

    constructor()
    constructor(
        id: Long?,
        name: String?,
        created: Long?,
        price: Int?,
        oldPrice: Int?,
        state: ProductState,
        sold: Long?,
        image: String?,
        description: String?,
        productType: ProductTypeEntity,
        client: ClientEntity?
    ) {
        this.id = id
        this.name = name
        this.created = created
        this.price = price
        this.oldPrice = oldPrice
        this.state = state
        this.sold = sold
        this.image = image
        this.description = description
        this.productType = productType
        this.client = client
    }


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
        other as ProductEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id, name = $name )"
    }
}
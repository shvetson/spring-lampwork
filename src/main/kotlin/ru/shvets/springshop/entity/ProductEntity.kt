package ru.shvets.springshop.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import org.hibernate.Hibernate
import ru.shvets.springshop.model.ProductState
import java.util.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  26.09.2022 14:24
 */

@Entity
@Table(name = "products")
class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "date_created", updatable = false, nullable = false)
    var created: Long? = null

    @Column(name = "price", nullable = false)
    var price: Int? = null

    @Column(name = "old_price", nullable = false)
    var oldPrice: Int? = null

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    var state: ProductState? = null

    @Column(name = "date_sold", nullable = false)
    var sold: Long? = null

    @Column(name = "image", nullable = false)
    var image: String? = null

    @Column(name = "description", nullable = true)
    var description: String? = null

    @ManyToOne(optional = false, cascade = [CascadeType.ALL])
    @JoinColumn(name = "product_type_id", nullable = false)
    @JsonManagedReference
    var productType: ProductTypeEntity? = null

    @ManyToOne(optional = true, cascade = [CascadeType.ALL])
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = true)
    var client: ClientEntity? = null

    @Column(name = "enabled", nullable = false)
    var enabled: Boolean? = null

    constructor()
    constructor(
        id: Long?,
        name: String?,
        created: Long?,
        price: Int?,
        oldPrice: Int?,
        state: ProductState?,
        sold: Long?,
        image: String?,
        description: String?,
        productType: ProductTypeEntity?,
        client: ClientEntity?,
        enabled: Boolean?
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
        this.enabled = enabled
    }

    @PrePersist
    fun onCreate() {
        created = Date().time
        oldPrice = 0
    }

    @PreUpdate
    fun onUpdate() {

        if (state == ProductState.SOLD) {
            if (client == null && sold == 0L) {
                sold = Date().time
                state = ProductState.SOLD
                oldPrice = 0
            } else {
                sold = Date().time
            }
        } else {
            if (client == null) {
                sold = 0
            }

            if (client != null) {
                if (sold == 0L) {
                    sold = Date().time
                    state = ProductState.SOLD
                    oldPrice = 0
                } else {
                    sold = 0
                    client = null
                    oldPrice = 0
                }
            }
        }

        if (oldPrice != 0) {
            state = ProductState.SALE
        }
    }
}
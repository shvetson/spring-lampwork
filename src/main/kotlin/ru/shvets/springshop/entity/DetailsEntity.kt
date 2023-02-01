package ru.shvets.springshop.entity

import jakarta.persistence.*
import ru.shvets.springshop.model.ProductParameter

@Entity
@Table(name = "details")
class DetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "created", nullable = false)
    var created: Long? = null

    @Enumerated
    @Column(name = "parameter", nullable = false)
    var parameter: ProductParameter? = null

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "order_id")
    var order: OrderEntity? = null
}
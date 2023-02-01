package ru.shvets.springshop.entity

import jakarta.persistence.*

@Entity
@Table(name = "orders")
class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    var id: Long? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    var client: ClientEntity? = null

    @Column(name = "date_created", nullable = false)
    var created: Long? = null

    @Column(name = "date_deadline", nullable = false)
    var deadline: Long? = null

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    var detailsList: MutableList<DetailsEntity> = mutableListOf()


}
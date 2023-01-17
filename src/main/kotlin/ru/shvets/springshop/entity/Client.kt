package ru.shvets.springshop.entity

import java.util.*
import javax.persistence.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  17.01.2023 16:32
 */

@Entity
@Table(name = "clients")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "first_name")
    val firstName: String,
    @Column(name = "last_name")
    val lastName: String,
    @Column(name = "date_created", updatable = false)
    var created: Long = Date().time,
    @Column(name = "date_updated")
    val updated: Long = Date().time,

    @OneToOne (optional= true, cascade=[CascadeType.ALL])
    @JoinColumn (name="address_id")
    val address: Address?,

    @Column(name = "email")
    val email: String,
    @Column(name = "phone")
    val phone: String,
    @Column(name = "telegram")
    val telegram: String,
    @Column(name = "whats_app")
    val whatsApp: String,
    @Column(name = "description")
    val description: String,

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    val shoppingList: Collection<Product>?
) {
        @PrePersist
    fun onCreate() {
        created = Date().time
    }
}
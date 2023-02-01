package ru.shvets.springshop.entity

import jakarta.persistence.*
import org.hibernate.Hibernate
import java.util.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  17.01.2023 16:32
 */

@Entity
@Table(name = "clients")
class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "first_name", nullable = false)
    var firstName: String? = null

    @Column(name = "last_name", nullable = false)
    var lastName: String? = null

    @Column(name = "date_created", updatable = false, nullable = false)
    var created: Long? = null //Date().time

    @Column(name = "date_updated", nullable = false)
    var updated: Long? = null

    @OneToOne(optional = false, cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    var address: AddressEntity? = null

    @Column(name = "email", unique = true, nullable = false)
    var email: String? = null

    @Column(name = "phone", nullable = false)
    var phone: String? = null

    @Column(name = "telegram", nullable = false)
    var telegram: String? = null

    @Column(name = "whats_app", nullable = false)
    var whatsApp: String? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    var shopping: MutableCollection<ProductEntity>? = null

    @PrePersist
    fun onCreate() {
        created = Date().time
        updated = Date().time
    }

    @PreUpdate
    fun onUpdate() {
        updated = Date().time
    }

    constructor()

    constructor(
        id: Long?,
        firstName: String?,
        lastName: String?,
        created: Long?,
        updated: Long?,
        address: AddressEntity?,
        email: String?,
        phone: String?,
        telegram: String?,
        whatsApp: String?,
        description: String?,
//        shopping: MutableCollection<ProductEntity>?
    ) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.created = created
        this.updated = updated
        this.address = address
        this.email = email
        this.phone = phone
        this.telegram = telegram
        this.whatsApp = whatsApp
        this.description = description
//        this.shopping = shopping
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ClientEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , firstName = $firstName , lastName = $lastName )"
    }
}
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
class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "first_name", nullable = false)
    var firstName: String? = null

    @Column(name = "last_name", nullable = false)
    var lastName: String? = null

    @Column(name = "date_created", updatable = false, nullable = false)
    var created: Long? = Date().time

    @Column(name = "date_updated", nullable = false)
    var updated: Long? = Date().time

    @OneToOne(optional = false, cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    var address: AddressEntity = AddressEntity()

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

//    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "client", fetch = FetchType.EAGER)
//    var shopping: MutableCollection<ProductEntity> = mutableListOf()

    constructor()
    constructor(
        id: Long?,
        firstName: String?,
        lastName: String?,
        created: Long?,
        updated: Long?,
        address: AddressEntity,
        email: String?,
        phone: String?,
        telegram: String?,
        whatsApp: String?,
        description: String?,
//        shopping: MutableCollection<ProductEntity>
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
}
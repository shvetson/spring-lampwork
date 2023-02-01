package ru.shvets.springshop.entity

import jakarta.persistence.*
import org.hibernate.Hibernate

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  17.01.2023 16:45
 */

@Entity
@Table(name = "addresses")
class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "code", nullable = true)
    var code: String? = null

    @Column(name = "city", nullable = false)
    var city: String? = null

    @Column(name = "region", nullable = true)
    var region: String? = null

    @Column(name = "area", nullable = true)
    var area: String? = null

    @Column(name = "street", nullable = false)
    var street: String? = null

    @Column(name = "building", nullable = false)
    var building: String? = null

    @Column(name = "housing", nullable = true)
    var housing: String? = null

    @Column(name = "flat", nullable = false)
    var flat: Int? = null

    constructor()
    constructor(
        id: Long?,
        code: String?,
        city: String?,
        region: String?,
        area: String?,
        street: String?,
        building: String?,
        housing: String?,
        flat: Int?
    ) {
        this.id = id
        this.code = code
        this.city = city
        this.region = region
        this.area = area
        this.street = street
        this.building = building
        this.housing = housing
        this.flat = flat
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as AddressEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , city = $city )"
    }
}
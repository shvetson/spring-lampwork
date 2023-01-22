package ru.shvets.springshop.entity

import javax.persistence.*

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

    @Column(name = "code", nullable = false)
    var code: String? = null

    @Column(name = "city", nullable = false)
    var city: String? = null

    @Column(name = "region", nullable = false)
    var region: String? = null

    @Column(name = "area", nullable = false)
    var area: String? = null

    @Column(name = "street", nullable = false)
    var street: String? = null

    @Column(name = "building", nullable = false)
    var building: String? = null

    @Column(name = "housing", nullable = false)
    var housing: String? = null

    @Column(name = "flat", nullable = false)
    var flat: Int? = null

//    @OneToOne(optional = false, mappedBy = "address")
//    val client: ClientEntity = ClientEntity()

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
}
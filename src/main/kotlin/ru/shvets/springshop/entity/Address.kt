package ru.shvets.springshop.entity

import javax.persistence.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  17.01.2023 16:45
 */

@Entity
@Table(name="addresses")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "code")
    val code: Long,
    @Column(name = "city")
    val city: String,
    @Column(name = "region")
    val region: String,
    @Column(name = "street")
    val street: String,
    @Column(name = "building")
    val building: Int,
    @Column(name = "flat")
    val flat: Int,

    @OneToOne (optional=false, mappedBy="address")
    val client: Client
)
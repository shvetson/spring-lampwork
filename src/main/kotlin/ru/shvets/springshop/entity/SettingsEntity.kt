package ru.shvets.springshop.entity

import jakarta.persistence.*


/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  09.10.2022 19:00
 */

@Entity
@Table(name = "settings")
class SettingsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "value")
    val value: Int
)
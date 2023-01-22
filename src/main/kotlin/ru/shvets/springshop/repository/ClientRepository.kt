package ru.shvets.springshop.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.shvets.springshop.entity.ClientEntity

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  17.01.2023 20:48
 */

@Repository
interface ClientRepository: JpaRepository<ClientEntity, Long> {
}
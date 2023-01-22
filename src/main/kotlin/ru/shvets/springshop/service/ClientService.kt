package ru.shvets.springshop.service

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.shvets.springshop.dto.AddressDto
import ru.shvets.springshop.dto.ClientDto
import ru.shvets.springshop.dto.ClientDto.Companion.toDto
import ru.shvets.springshop.entity.AddressEntity
import ru.shvets.springshop.entity.ClientEntity
import ru.shvets.springshop.repository.AddressRepository
import ru.shvets.springshop.repository.ClientRepository

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  17.01.2023 20:49
 */


interface ClientService {
    fun getAllClients(): List<ClientDto>
    fun getClientById(id: Long): ClientDto
    fun createClient(): ClientDto
    fun saveClient(client: ClientDto)
    fun deleteClient(id: Long)
}
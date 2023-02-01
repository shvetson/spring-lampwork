package ru.shvets.springshop.service

import ru.shvets.springshop.dto.ClientDto

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
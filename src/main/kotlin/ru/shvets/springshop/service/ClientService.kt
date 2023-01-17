package ru.shvets.springshop.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.shvets.springshop.entity.Client
import ru.shvets.springshop.entity.Product
import ru.shvets.springshop.entity.ProductType
import ru.shvets.springshop.repository.AddressRepository
import ru.shvets.springshop.repository.ClientRepository

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  17.01.2023 20:49
 */

@Service
class ClientService(
    private val clientRepository: ClientRepository,
    private val addressRepository: AddressRepository
) {
    private val logger = KotlinLogging.logger {}

    fun getAll(): List<Client> {
        return clientRepository.findAll()
    }

    fun saveClient(client: Client) {
        clientRepository.save(client)
    }

    fun createClient(): Client {
        return Client(
            id = 0L,
            firstName = "",
            lastName = "",
            address = null,
            email = "",
            phone = "",
            telegram = "",
            whatsApp = "",
            description = "",
            shoppingList = null
        )
    }
}
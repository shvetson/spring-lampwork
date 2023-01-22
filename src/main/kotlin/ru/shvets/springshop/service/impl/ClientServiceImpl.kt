package ru.shvets.springshop.service.impl

import mu.KotlinLogging
import org.springframework.data.domain.Sort
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
import ru.shvets.springshop.service.ClientService

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  17.01.2023 20:49
 */

@Service
class ClientServiceImpl(
    private val clientRepository: ClientRepository,
    private val addressRepository: AddressRepository
) : ClientService {
    private val logger = KotlinLogging.logger {}

    override fun getAllClients(): List<ClientDto> {
        return clientRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName")).map {
            it.toDto()
        }
    }

    override fun getClientById(id: Long): ClientDto {
        val client = clientRepository.findById(id).orElse(null) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Данные о клиенте не найдены."
        )
        return client.toDto()
    }

    override fun saveClient(client: ClientDto) {
        clientRepository.save(client.toEntity())
        logger.info("Данные о клиенте добавлены / обновлены.")
    }

    override fun deleteClient(id: Long) {
        clientRepository.deleteById(id)
        logger.info("Данные о клиенте удалены.")
    }

    override fun createClient(): ClientDto {
        return ClientDto()
    }
}
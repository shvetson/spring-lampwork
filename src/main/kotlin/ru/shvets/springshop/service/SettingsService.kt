package ru.shvets.springshop.service

import org.springframework.stereotype.Service
import ru.shvets.springshop.api.response.SettingsResponse

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  10.01.2023 23:40
 */

@Service
class SettingsService {

    fun getGlobalSettings(): SettingsResponse {
        return SettingsResponse(
            pagingLimit = 10,
            pagingOffset = 0,
            countDaysWithoutActivity = 60,
            authenticationMode = true
        )
    }
}
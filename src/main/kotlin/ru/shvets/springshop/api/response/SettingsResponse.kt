package ru.shvets.springshop.api.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.security.core.Authentication

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  10.01.2023 22:57
 */

class SettingsResponse {
    @JsonProperty("PAGING_COUNT")
    var pagingCount: Int? = null
    @JsonProperty("COUNT_DAYS_WITHOUT_ACTIVITY")
    var countDaysWithoutActivity: Int? = null
    @JsonProperty("AUTHENTICATION_MODE")
    var authenticationMode: Boolean? = null

    constructor()
    constructor(
        pagingCount: Int,
        countDaysWithoutActivity: Int,
        authenticationMode: Boolean
    ): super() {
        this.pagingCount = pagingCount
        this.countDaysWithoutActivity = countDaysWithoutActivity
        this.authenticationMode = authenticationMode
    }
}
package ru.shvets.springshop.api.response

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  10.01.2023 22:57
 */

data class SettingsResponse(
    @JsonProperty("PAGING_LIMIT")
    val pagingLimit: Int?,
    @JsonProperty("PAGING_OFFSET")
    val pagingOffset: Int?,
    @JsonProperty("COUNT_DAYS_WITHOUT_ACTIVITY")
    val countDaysWithoutActivity: Int?,
    @JsonProperty("AUTHENTICATION_MODE")
    val authenticationMode: Boolean?
)
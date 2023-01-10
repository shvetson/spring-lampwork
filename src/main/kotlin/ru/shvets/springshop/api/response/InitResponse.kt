package ru.shvets.springshop.api.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  10.01.2023 20:21
 */

@Component
class InitResponse {
    @Value("\${app.title}")
    @JsonProperty("title")
    var title: String? = null

    @Value("\${app.subtitle}")
    @JsonProperty("subtitle")
    var subtitle: String? = null

    @Value("\${app.email}")
    @JsonProperty("email")
    var email: String? = null

    @Value("\${app.phone}")
    @JsonProperty("mobile")
    var phone: String? = null

    @Value("\${app.copyright}")
    @JsonProperty("copyright")
    var copyright: String? = null

    @Value("\${app.copyrightFrom}")
    @JsonProperty("copyright_from")
    var copyrightFrom: String? = null

    constructor() {}

    constructor(
        title: String,
        subtitle: String,
        email: String,
        phone: String,
        copyright: String,
        copyrightFrom: String
    ) : super() {
        this.title = title
        this.subtitle = subtitle
        this.email = email
        this.phone = phone
        this.copyright = copyright
        this.copyrightFrom = copyrightFrom
    }
}
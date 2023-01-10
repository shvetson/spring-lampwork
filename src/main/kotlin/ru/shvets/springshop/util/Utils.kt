package ru.shvets.springshop.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author  Oleg Shvets
 * @version 1.0
 * @date  31.12.2022 00:37
 */

object Utils {

    fun Long.toDate(): String = SimpleDateFormat("dd/MM/yyyy").format(Date(this))
}
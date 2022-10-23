package ru.shvets.springshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringShopApplication

fun main(args: Array<String>) {
    runApplication<SpringShopApplication>(*args)
}

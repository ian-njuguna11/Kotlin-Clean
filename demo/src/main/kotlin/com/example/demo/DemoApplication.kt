package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Repository


@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	@Bean
	fun controller() = UserController()

	runApplication<DemoApplication>(*args)
}

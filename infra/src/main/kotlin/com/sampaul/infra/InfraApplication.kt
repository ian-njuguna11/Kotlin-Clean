package com.sampaul.infra

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class InfraApplication

fun main(args: Array<String>) {
	SpringApplication.run(InfraApplication::class.java, *args)
}


package com.example.kotlingraphql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinGraphqlApplication

fun main(args: Array<String>) {
	runApplication<KotlinGraphqlApplication>(*args)
}
package com.demo.kafkaelk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaElkApplication

fun main(args: Array<String>) {
	runApplication<KafkaElkApplication>(*args)
}
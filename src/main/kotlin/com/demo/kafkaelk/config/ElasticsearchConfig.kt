package com.demo.kafkaelk.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients

@Configuration
class ElasticsearchConfig {

	@Bean
	fun reactiveElasticsearchClient(): ReactiveElasticsearchClient {
		val clientConfiguration = ClientConfiguration.builder()
			.connectedTo("localhost:9200")
			.build()
		return ReactiveRestClients.create(clientConfiguration)
	}

}

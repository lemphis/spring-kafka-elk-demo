package com.demo.kafkaelk.repository

import com.demo.kafkaelk.entity.Log
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository
import reactor.core.publisher.Flux

interface ElasticsearchRepository : ReactiveElasticsearchRepository<Log, String> {

	fun findByMessage(message: String): Flux<Log>

}

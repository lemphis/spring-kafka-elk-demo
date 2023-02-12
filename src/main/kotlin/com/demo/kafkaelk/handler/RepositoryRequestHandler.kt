package com.demo.kafkaelk.handler

import com.demo.kafkaelk.entity.Log
import com.demo.kafkaelk.repository.ElasticsearchRepository
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody

@Component
class RepositoryRequestHandler(
	private val elasticsearchRepository: ElasticsearchRepository,
) {

	suspend fun getAll(): ServerResponse {
		val logs = elasticsearchRepository.findAll().awaitSingle()
		return ServerResponse.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(mapOf("indices" to logs))
			.awaitSingle()
	}

	suspend fun get(request: ServerRequest): ServerResponse {
		val logs = elasticsearchRepository.findByMessage(request.pathVariable("message"))
		return ServerResponse.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(mapOf("indices" to logs))
			.awaitSingle()
	}

	suspend fun put(request: ServerRequest): ServerResponse {
		val log = request.awaitBody<Log>()
		elasticsearchRepository.save(log).subscribe()
		return ServerResponse.noContent()
			.build()
			.awaitSingle()
	}

}

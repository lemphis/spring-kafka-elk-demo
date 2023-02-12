package com.demo.kafkaelk.handler

import com.demo.kafkaelk.entity.Log
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import org.elasticsearch.client.indices.GetIndexRequest
import org.elasticsearch.xcontent.XContentType
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody

@Component
class ClientRequestHandler(
	private val client: ReactiveElasticsearchClient,
	private val mapper: ObjectMapper,
) {

	suspend fun indices(): ServerResponse {
		val indices = client.indices()
			.getIndex(GetIndexRequest("log-*"))
			.awaitSingle()
		return ServerResponse.ok()
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(indices.indices)
			.awaitSingle()
	}

//	suspend fun getAll(request: ServerRequest): ServerResponse {
//		val logs =
//			client.search { it.indices("log-2023-01-01").source(SearchSourceBuilder.searchSource()) }.awaitSingle()
//		return ServerResponse.ok()
//			.contentType(MediaType.APPLICATION_JSON)
//			.bodyValue(mapOf("indices" to logs))
//			.awaitSingle()
//	}

	suspend fun put(request: ServerRequest): ServerResponse {
		val log = request.awaitBody<Log>()
		client.index { it.index("log-2023-01-01").source(mapper.writeValueAsString(log), XContentType.JSON) }
			.subscribe()
		return ServerResponse.noContent()
			.build()
			.awaitSingle()
	}

}

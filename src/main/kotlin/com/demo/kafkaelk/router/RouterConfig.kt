package com.demo.kafkaelk.router

import com.demo.kafkaelk.handler.ClientRequestHandler
import com.demo.kafkaelk.handler.RepositoryRequestHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfig(
	private val clientRequestHandler: ClientRequestHandler,
	private val repositoryRequestHandler: RepositoryRequestHandler,
) {

	@Bean
	fun apiRouter() = coRouter {
		(accept(APPLICATION_JSON) and "/client").nest {
			GET("indices") { clientRequestHandler.indices() }
			GET("/log") { clientRequestHandler.getAll(it) }
//			GET("/log/{message}") { clientRequestHandler.get(it) }
			PUT("/log") { clientRequestHandler.put(it) }
		}
		(accept(APPLICATION_JSON) and "/repository").nest {
			GET("/log") { repositoryRequestHandler.getAll() }
			GET("/log/{message}") { repositoryRequestHandler.get(it) }
			PUT("/log") { repositoryRequestHandler.put(it) }
		}
	}

}

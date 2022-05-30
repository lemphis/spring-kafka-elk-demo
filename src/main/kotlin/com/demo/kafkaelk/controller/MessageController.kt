package com.demo.kafkaelk.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class MessageController {

	@PostMapping(value = ["/test"])
	fun handleMessage(): Mono<String> {
		return Mono.just("idontknow")
	}

}

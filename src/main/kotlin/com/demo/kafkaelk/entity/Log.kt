package com.demo.kafkaelk.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import java.time.LocalDateTime
import java.util.UUID

@Document(indexName = "log-2023-01-01")
class Log(
	@Id
	var id: String = UUID.randomUUID().toString(),
	var name: String? = null,
	var message: String? = null,
	var timestamp: LocalDateTime = LocalDateTime.now(),
)

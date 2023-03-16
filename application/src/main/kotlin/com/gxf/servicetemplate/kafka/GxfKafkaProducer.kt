package com.gxf.servicetemplate.kafka

import com.gxf.servicetemplate.measurement.MeasurementGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random


@Service
class GxfKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    private val measurementGenerator: MeasurementGenerator
) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Scheduled(cron = "*/10 * * * * *")
    fun producer() {
        logger.info("Producing: ${DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())}")

        kafkaTemplate.send("avroTopic", measurementGenerator.generateMeasurement(Random.nextLong()))
    }
}

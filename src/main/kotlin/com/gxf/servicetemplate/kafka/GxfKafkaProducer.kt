package com.gxf.servicetemplate.kafka

import com.gxf.service.Measurement
import com.gxf.servicetemplate.measurement.MeasurementGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Service
class GxfKafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, Measurement>,
    private val measurementGenerator: MeasurementGenerator
) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Scheduled(cron = "* * * * * *")
    fun producer() {
        logger.info("Producing: ${DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())}")
        (1000..2000).forEach {
            kafkaTemplate.send("avroTopic", measurementGenerator.generateMeasurement(it.toLong()))
        }
        logger.info("Produced: ${DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now())}")
    }
}

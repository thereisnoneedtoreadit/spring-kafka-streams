package com.example.springkafkastreams.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.errors.ProductionExceptionHandler;

import java.util.Map;

@Slf4j
public class KafkaStreamProcessingExceptionHandler implements ProductionExceptionHandler {

    @Override
    public void configure(Map<String, ?> map) {
    }

    @Override
    public ProductionExceptionHandlerResponse handle(final ProducerRecord<byte[], byte[]> record,
                                                     final Exception exception) {
        log.error("[topic: '{}', value: '{}', exceptionMessage: '{}']",
                record.topic(), record.value(), exception.getMessage());
        return ProductionExceptionHandlerResponse.CONTINUE;
    }

}
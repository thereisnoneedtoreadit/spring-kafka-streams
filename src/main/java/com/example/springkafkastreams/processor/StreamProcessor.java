package com.example.springkafkastreams.processor;

import org.apache.kafka.streams.kstream.KStream;

public interface StreamProcessor<T> {
    void process(KStream<String, T> inputStream);
}

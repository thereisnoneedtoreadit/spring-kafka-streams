package com.example.springkafkastreams.processor;

import com.example.springkafkastreams.dto.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskProcessor implements StreamProcessor<Task> {

    @Override
    public void process(KStream<String, Task> inputStream) {
        inputStream
                .foreach((s, task) -> {
                    log.info("Task received: {}", task);
                });
    }

}
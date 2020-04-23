package com.example.springkafkastreams.configuration.serde.deserializer;

import com.example.springkafkastreams.dto.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskDeserializer implements Deserializer<Task> {

    private final ObjectMapper mapper;

    @Override
    @SneakyThrows
    public Task deserialize(String s, byte[] bytes) {
        return mapper.readValue(bytes, Task.class);
    }

}

package com.example.springkafkastreams.configuration.serde.serializer;

import com.example.springkafkastreams.dto.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskSerializer implements Serializer<Task> {

    private final ObjectMapper mapper;

    @Override
    @SneakyThrows
    public byte[] serialize(String s, Task task) {
        return mapper.writeValueAsBytes(task);
    }
}

package com.example.springkafkastreams.configuration.serde;

import com.example.springkafkastreams.configuration.serde.deserializer.TaskDeserializer;
import com.example.springkafkastreams.configuration.serde.serializer.TaskSerializer;
import com.example.springkafkastreams.dto.Task;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskSerde implements Serde<Task> {

    private final TaskSerializer serializer;
    private final TaskDeserializer deserializer;

    @Override
    public Serializer<Task> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<Task> deserializer() {
        return deserializer;
    }

}

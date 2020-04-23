package com.example.springkafkastreams.configuration.kafka;

import com.example.springkafkastreams.configuration.serde.TaskSerde;
import com.example.springkafkastreams.dto.Task;
import com.example.springkafkastreams.processor.StreamProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Configuration
public class TaskProcessorsConfig {

    @Value("${kafka.tasks.topic}")
    private String tasksTopic;
    private final TaskSerde taskSerde;
    private final StreamsBuilder kStreamBuilder;
    private final List<StreamProcessor<Task>> processors;

    public TaskProcessorsConfig(TaskSerde taskSerde, StreamsBuilder kStreamBuilder, List<StreamProcessor<Task>> processors) {
        this.taskSerde = taskSerde;
        this.kStreamBuilder = kStreamBuilder;
        this.processors = processors;
    }

    @PostConstruct
    void init() {
        KStream<String, Task> kStream = initKStream();
        processors.forEach(process -> process.process(kStream));
    }

    public KStream<String, Task> initKStream() {
        return kStreamBuilder.stream(tasksTopic, Consumed.with(Serdes.String(), taskSerde))
                .filter(this::notNull)
                .peek((k, v) -> log.info("TasksInputStream. [ {} ]", v))
                .mapValues(task -> new Task("changedName", task.getDescription(), task.getTime()));
    }

    private boolean notNull(String ignoreKey, Task task) {
        return task != null;
    }

}
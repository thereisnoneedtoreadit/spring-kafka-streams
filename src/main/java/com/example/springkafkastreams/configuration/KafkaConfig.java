package com.example.springkafkastreams.configuration;

import com.example.springkafkastreams.exception.handler.KafkaStreamProcessingExceptionHandler;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.CleanupConfig;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_BUILDER_BEAN_NAME)
    public StreamsBuilderFactoryBean streamsBuilderFactoryBean(KafkaProperties kafkaProperties) {
        KafkaStreamsConfiguration configuration = getStreamsConfigs(kafkaProperties);
        CleanupConfig cleanupConfig = new CleanupConfig(true, true);
        return new StreamsBuilderFactoryBean(configuration, cleanupConfig);
    }

    private KafkaStreamsConfiguration getStreamsConfigs(KafkaProperties kafkaProperties) {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaProperties.getClientId());
        config.put(StreamsConfig.DEFAULT_PRODUCTION_EXCEPTION_HANDLER_CLASS_CONFIG,
                KafkaStreamProcessingExceptionHandler.class);
        return new KafkaStreamsConfiguration(config);
    }

}

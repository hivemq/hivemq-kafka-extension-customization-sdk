package com.hivemq.extensions.kafka.api.services;

import com.hivemq.extension.sdk.api.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public interface KafkaTopicService {

    @NotNull KafkaTopicState getKafkaTopicState(@NotNull String topic);

    @NotNull Map<String, @NotNull KafkaTopicState> getKafkaTopicStates(@NotNull Set<@NotNull String> topics);

    @NotNull KafkaTopicState createKafkaTopic(@NotNull String topic);

    @NotNull Map<String, @NotNull KafkaTopicState> createKafkaTopics(@NotNull Set<@NotNull String> topics);

    enum KafkaTopicState {
        FAILURE, EXISTS, CREATED, MISSING,
    }
}

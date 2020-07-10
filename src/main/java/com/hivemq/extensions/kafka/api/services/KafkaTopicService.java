package com.hivemq.extensions.kafka.api.services;

import com.hivemq.extension.sdk.api.annotations.NotNull;

import java.util.List;

public interface KafkaTopicService {

    @NotNull KafkaTopicState getKafkaTopicState(@NotNull String topic);

    @NotNull List<@NotNull KafkaTopicState> getKafkaTopicStates(@NotNull List<@NotNull String> topics);

    @NotNull KafkaTopicState createKafkaTopic(@NotNull String topic);

    @NotNull List<@NotNull KafkaTopicState> createKafkaTopics(@NotNull List<@NotNull String> topics);

    enum KafkaTopicState {
        FAILURE, EXISTS, CREATED, MISSING,
    }
}

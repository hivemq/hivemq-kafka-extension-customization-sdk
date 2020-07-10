package com.hivemq.extensions.kafka.api.transformers.mqtttokafka;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.kafka.api.services.KafkaTopicService;
import com.hivemq.extensions.kafka.api.transformers.TransformerInitInput;

/**
 * @author Georg Held
 */
public interface MqttToKafkaInitInput extends TransformerInitInput {

    @NotNull KafkaTopicService getKafkaTopicService();
}

package com.hivemq.extensions.kafka.api.transformers.kafkatomqtt;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.kafka.api.model.KafkaCluster;
import com.hivemq.extensions.kafka.api.model.KafkaRecord;

/**
 * @author Christoph Schäbel
 */
public interface KafkaToMqttInput {

    @NotNull KafkaRecord getKafkaRecord();

    @NotNull KafkaCluster getKafkaCluster();

}

package com.hivemq.extensions.kafka.api.transformers.kafkatomqtt;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.kafka.api.transformers.Transformer;

/**
 * @author Christoph Schäbel
 */
public interface KafkaToMqttTransformer extends Transformer<KafkaToMqttInitInput> {

    void transformKafkaToMqtt(@NotNull KafkaToMqttInput input, @NotNull KafkaToMqttOutput output);
}

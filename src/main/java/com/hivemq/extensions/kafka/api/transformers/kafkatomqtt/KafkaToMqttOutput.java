package com.hivemq.extensions.kafka.api.transformers.kafkatomqtt;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.services.builder.PublishBuilder;
import com.hivemq.extension.sdk.api.services.publish.Publish;

import java.util.List;

/**
 * @author Christoph Schäbel
 */
public interface KafkaToMqttOutput {

    @NotNull PublishBuilder newPublishBuilder();

    void setMqttPublishes(List<Publish> kafkaRecord);
}

package com.hivemq.extensions.kafka.api.transformers.kafkatomqtt;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.kafka.api.model.KafkaCluster;
import com.hivemq.extensions.kafka.api.model.KafkaRecord;

/**
 * The input parameter of the {@link KafkaToMqttTransformer}. It contains the information of the to be transformed
 * {@link KafkaRecord}.
 * <p>
 * The MqttToKafkaInput allows access to the {@link KafkaCluster}.
 *
 * @author Christoph Schäbel
 * @since 4.5.0
 */
@Immutable
@DoNotImplement
public interface KafkaToMqttInput {

    /**
     * @return the {@link KafkaRecord} that triggered this transformer call.
     * @since 4.5.0
     */
    @NotNull KafkaRecord getKafkaRecord();

    /**
     * @return the {@link KafkaCluster} the transformer is associated with.
     * @since 4.5.0
     */
    @NotNull KafkaCluster getKafkaCluster();
}

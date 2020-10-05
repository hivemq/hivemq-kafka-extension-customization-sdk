package com.hivemq.extensions.kafka.api.transformers.kafkatomqtt;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.kafka.api.transformers.Transformer;

/**
 * Implement this transformer for the programmatic creation of {@link com.hivemq.extension.sdk.api.services.publish.Publish
 * Publishes} from {@link com.hivemq.extensions.kafka.api.model.KafkaRecord KafkaRecords}.
 * <p>
 * Your implementation of the KafkaToMqttTransformer must be placed in a Java archive (.jar) together with all its
 * dependencies in the {@code customizations} folder of the HiveMQ Enterprise Extension for Kafka. In addition a {@code
 * <kafka-to-mqtt-transformer>} referencing the implementing class via its canonical name must be configured in
 * the {@code kafka-extension.xml} file.
 *
 * @author Christoph Schäbel
 * @since 4.5.0
 */
@FunctionalInterface
public interface KafkaToMqttTransformer extends Transformer<KafkaToMqttInitInput> {

    /**
     * This callback is executed for every {@link com.hivemq.extensions.kafka.api.model.KafkaRecord KafkaRecord} that is
     * polled by the HiveMQ Enterprise Extension for Kafka and matches the {@code <mqtt-to-kafka-transformer>} tag
     * configured in the {@code <mqtt-topic-filters>}. It allows the publication of any number of {@link
     * com.hivemq.extension.sdk.api.services.publish.Publish Publishes} via the {@link KafkaToMqttOutput} object.
     *
     * @param input  the {@link KafkaToMqttInput} contains the triggering {@link com.hivemq.extensions.kafka.api.model.KafkaRecord
     *               KafkaRecord} and the {@link com.hivemq.extensions.kafka.api.model.KafkaCluster KafkaCluster}
     *               information.
     * @param output the {@link KafkaToMqttOutput} allows to {@link KafkaToMqttOutput#setMqttPublishes(java.util.List)
     *               provide a list of Publishes}.
     * @since 4.5.0
     */
    void transformKafkaToMqtt(@NotNull KafkaToMqttInput input, @NotNull KafkaToMqttOutput output);
}

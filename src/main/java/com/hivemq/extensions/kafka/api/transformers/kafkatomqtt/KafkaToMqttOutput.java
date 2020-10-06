package com.hivemq.extensions.kafka.api.transformers.kafkatomqtt;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.services.builder.PublishBuilder;
import com.hivemq.extension.sdk.api.services.publish.Publish;

import java.util.List;

/**
 * The output parameter of the {@link KafkaToMqttTransformer}. It allows access to the {@link PublishBuilder}.
 * <p>
 * After the {@link KafkaToMqttTransformer#transformKafkaToMqtt(KafkaToMqttInput, KafkaToMqttOutput)} method returns the
 * {@link Publish}s given to this output will be published by HiveMQ.
 *
 * @author Christoph Schäbel
 * @since 4.5.0
 */
@DoNotImplement
public interface KafkaToMqttOutput {

    /**
     * @return an new {@link PublishBuilder}.
     * @since 4.5.0
     */
    @NotNull PublishBuilder newPublishBuilder();

    /**
     * Sets the {@link Publish}s, that will be published by HiveMQ after the {@link
     * KafkaToMqttTransformer#transformKafkaToMqtt(KafkaToMqttInput, KafkaToMqttOutput)} call returns. The HiveMQ
     * Enterprise Extension for Kafka will publish these publishes in the order provided by the {@code publishes}
     * argument.
     * <p>
     * If desired, the same publish can occupy multiple places in the {@code publishes} list. When no publish shall be
     * published by HiveMQ for a {@link com.hivemq.extensions.kafka.api.model.KafkaRecord}, call this method with an
     * empty list.
     * <p>
     * Use the {@link #newPublishBuilder() PublishBuilder} to create new publishes as desired.
     * <p>
     * Each additional call of this method will overwrite the previous one.
     *
     * @param publishes a list of to be published {@link Publish}s.
     * @throws NullPointerException     if {@code publishes} or any element of it is null.
     * @throws IllegalArgumentException if any element in {@code publishes} was not created via a {@link
     *                                  PublishBuilder}.
     * @since 4.5.0
     */
    void setPublishes(@NotNull List<@NotNull Publish> publishes);
}

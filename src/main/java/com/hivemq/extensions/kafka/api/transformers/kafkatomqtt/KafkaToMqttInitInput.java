package com.hivemq.extensions.kafka.api.transformers.kafkatomqtt;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extensions.kafka.api.transformers.TransformerInitInput;

/**
 * Provides context for the set up of a {@link KafkaToMqttTransformer}.
 *
 * @author Georg Held
 * @since 4.5.0
 */
@Immutable
@DoNotImplement
public interface KafkaToMqttInitInput extends TransformerInitInput {}

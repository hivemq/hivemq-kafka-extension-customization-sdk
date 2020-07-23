/*
 * Copyright 2020-present HiveMQ GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hivemq.extensions.kafka.api.transformers.mqtttokafka;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.kafka.api.model.KafkaCluster;
import com.hivemq.extensions.kafka.api.services.KafkaTopicService;
import com.hivemq.extensions.kafka.api.transformers.TransformerInitInput;

/**
 * Provides context for the set up of a {@link MqttToKafkaTransformer}.
 *
 * @author Georg Held
 * @since 4.4.0
 */
@Immutable
@DoNotImplement
public interface MqttToKafkaInitInput extends TransformerInitInput {

    /**
     * @return the {@link KafkaCluster} this transformer is associated with.
     * @since 4.4.0
     */
    @NotNull KafkaCluster getKafkaCluster();

    /**
     * @return the {@link KafkaTopicService} to interact with topics on the Kafka cluster.
     * @since 4.4.0
     */
    @NotNull KafkaTopicService getKafkaTopicService();
}

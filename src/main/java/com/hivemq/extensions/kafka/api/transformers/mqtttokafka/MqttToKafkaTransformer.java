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

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.kafka.api.transformers.Transformer;

import java.util.List;

/**
 * Implement this transformer for the programmatic creation of {@link com.hivemq.extensions.kafka.api.model.KafkaRecord}s
 * from {@link com.hivemq.extension.sdk.api.packets.publish.PublishPacket}s.
 * <p>
 * Your implementation of the MqttToKafkaTransformer must be placed in a java archive (.jar) together with all its
 * dependencies in the {@code extensions} folder of the HiveMQ Enterprise Extension for Kafka. In addition a {@code
 * <mqtt-to-kafka-transformer>} referencing the implementing class via its canonical name must be configured in the
 * {@code kafka-extension.xml} file.
 *
 * @author Christoph Schäbel
 * @author Georg Held
 * @since 4.4.0
 */
public interface MqttToKafkaTransformer extends Transformer<MqttToKafkaInitInput> {

    /**
     * This callback is executed for every MQTT PUBLISH that arrives at your HiveMQ cluster matching the in the {@code
     * <mqtt-to-kafka-transformer>} tag configured {@code <mqtt-topic-filters>}. It allows the publication of any number
     * of {@link com.hivemq.extensions.kafka.api.model.KafkaRecord}s via the {@link MqttToKafkaOutput} object.
     *
     * @param input  the {@link MqttToKafkaInput} contains the triggering {@link com.hivemq.extension.sdk.api.packets.publish.PublishPacket}
     *               and the {@link com.hivemq.extensions.kafka.api.model.KafkaCluster} information. The {@link
     *               com.hivemq.extensions.kafka.api.services.KafkaTopicService} can be accessed.
     * @param output pass the list of new {@link com.hivemq.extensions.kafka.api.model.KafkaRecord}s to the {@link
     *               MqttToKafkaOutput#setKafkaRecords(List)} methods.
     * @since 4.4.0
     */
    void transformMqttToKafka(@NotNull MqttToKafkaInput input, @NotNull MqttToKafkaOutput output);
}
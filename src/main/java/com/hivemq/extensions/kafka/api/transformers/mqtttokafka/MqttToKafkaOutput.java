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
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.kafka.api.builders.KafkaRecordBuilder;
import com.hivemq.extensions.kafka.api.model.KafkaRecord;

import java.util.List;

/**
 * The output parameter of the {@link MqttToKafkaTransformer}. It allows access to the {@link KafkaRecordBuilder}.
 * <p>
 * After the {@link MqttToKafkaTransformer#transformMqttToKafka(MqttToKafkaInput, MqttToKafkaOutput)} method returns the
 * {@link KafkaRecord}s given to this output will be published to the associated Kafka cluster by the HiveMQ Enterprise
 * Extension for Kafka.
 *
 * @author Christoph Schäbel
 * @author Georg Held
 * @since 4.4.0
 */
@DoNotImplement
public interface MqttToKafkaOutput {

    /**
     * Create a new {@link KafkaRecordBuilder}. One {@link KafkaRecordBuilder} can be used to build multiple Kafka
     * records.
     *
     * @return an empty instance of the {@link KafkaRecordBuilder}.
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder newKafkaRecordBuilder();

    /**
     * Sets the {@link KafkaRecord}s, that will be pushed to the associated Kafka cluster after the {@link
     * MqttToKafkaTransformer#transformMqttToKafka(MqttToKafkaInput, MqttToKafkaOutput)} call returns. The HiveMQ
     * Enterprise Extension for Kafka will publish these records in the order provided by the {@code kafkaRecords}
     * argument.
     * <p>
     * If desired, the same record can occupy multiple places in the {@code kafkaRecords} list. When no record shall be
     * pushed to the associated Kafka cluster for a {@link com.hivemq.extension.sdk.api.packets.publish.PublishPacket},
     * call this method with an empty list.
     * <p>
     * Use the {@link KafkaRecordBuilder} to create new records as desired.
     * <p>
     * Each additional call of this method will overwrite the previous one.
     *
     * @param kafkaRecords a list of to be published {@link KafkaRecord}s.
     * @throws NullPointerException     if {@code kafkaRecords} or any element of it is null.
     * @throws IllegalArgumentException if any element in {@code kafkaRecords} was not created via a {@link
     *                                  KafkaRecordBuilder}.
     * @since 4.4.0
     */
    void setKafkaRecords(@NotNull List<@NotNull KafkaRecord> kafkaRecords);
}

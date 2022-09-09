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

package com.hivemq.extensions.kafka.api.transformers.kafkatomqtt;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.annotations.ThreadSafe;
import com.hivemq.extensions.kafka.api.transformers.Transformer;

/**
 * Implement this transformer for the programmatic creation of
 * {@link com.hivemq.extension.sdk.api.services.publish.Publish
 * Publishes} from {@link com.hivemq.extensions.kafka.api.model.KafkaRecord KafkaRecords}. One instance of the
 * implementing class is created per reference in the kafka-configuration.xml. The methods of this interface may be
 * called concurrently and must be thread-safe.
 * <p>
 * Your implementation of the KafkaToMqttTransformer must be placed in a Java archive (.jar) together with all its
 * dependencies in the {@code customizations} folder of the "HiveMQ Enterprise Extension for Kafka". In addition, a
 * {@code <kafka-to-mqtt-transformer>} referencing the implementing class via its canonical name must be configured in
 * the {@code kafka-extension.xml} file.
 *
 * @author Christoph Schäbel
 * @author Georg Held
 * @author Daniel Krüger
 * @since 4.5.0
 */
@FunctionalInterface
public interface KafkaToMqttTransformer extends Transformer<KafkaToMqttInitInput> {

    /**
     * This callback is executed for every {@link com.hivemq.extensions.kafka.api.model.KafkaRecord KafkaRecord} that
     * the "HiveMQ Enterprise Extension for Kafka" polls from Kafka according to the configured {@code <kafka-topics>}
     * in the {@code <kafka-to-mqtt-transformer>} tag.
     * <p>
     * It allows the publication of any number of {@link
     * com.hivemq.extension.sdk.api.services.publish.Publish Publishes} via the {@link KafkaToMqttOutput} object. This
     * method is called by multiple threads concurrently. Extensions are responsible for their own exception handling
     * and this method must not throw any {@link Exception}.
     *
     * @param input  The {@link KafkaToMqttInput} contains the triggering
     *               {@link com.hivemq.extensions.kafka.api.model.KafkaRecord
     *               KafkaRecord} and the {@link com.hivemq.extensions.kafka.api.model.KafkaCluster KafkaCluster}
     *               information.
     * @param output The {@link KafkaToMqttOutput} allows to {@link KafkaToMqttOutput#setPublishes(java.util.List)
     *               provide a list of Publishes}. If no output is set, an empty List is used as default and the kafka
     *               record will not be processed again, but ignored.
     * @since 4.5.0
     */
    @ThreadSafe
    void transformKafkaToMqtt(@NotNull KafkaToMqttInput input, @NotNull KafkaToMqttOutput output);
}

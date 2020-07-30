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
package com.hivemq.extensions.kafka.api.transformers;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;

/**
 * This is the base interface for all HiveMQ Enterprise Extension for Kafka transformer.
 *
 * @author Christoph Schäbel
 * @author Georg Held
 */
@DoNotImplement
public interface Transformer<I extends TransformerInitInput> {

    /**
     * Use the init method to set up static runtime context for the execution of your transformer.
     *
     * @param input see the specific input e.g. {@link com.hivemq.extensions.kafka.api.transformers.mqtttokafka.MqttToKafkaInitInput}.
     */
    default void init(@NotNull I input) {
    }
}

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
package com.hivemq.extensions.kafka.api.model;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * This interface contains all {@link KafkaHeader}s belonging to a single {@link KafkaRecord}.
 *
 * @author Christoph Schäbel
 * @author Georg Held
 * @since 4.4.0
 */
@Immutable
@DoNotImplement
public interface KafkaHeaders {

    /**
     * @return all Kafka headers as a {@link List}.
     */
    @Immutable @NotNull List<@NotNull KafkaHeader> asList();

    /**
     * @param name the name of the Kafka header to get.
     * @return an {@link Optional} that contains the last Kafka header with the specified name.
     * @since 4.3.0
     */
    @NotNull Optional<KafkaHeader> getLast(@NotNull String name);

    /**
     * @param name the name of the Kafka headers to get.
     * @return the values of the Kafka headers with the specified name.
     * @since 4.4.0
     */
    @Immutable @NotNull List<@NotNull KafkaHeader> getAllForName(@NotNull String name);

}

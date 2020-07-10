/*
 * Copyright 2018-present HiveMQ GmbH
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

import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * @author Christoph Schäbel
 */
public interface KafkaHeaders {

    @NotNull List<KafkaHeader> asList();

    /**
     * @param name The name of the user property to get.
     * @return An {@link Optional} that contains the last user property with the specified name.
     * @since 4.0.0
     */
    @NotNull Optional<KafkaHeader> getLast(@NotNull String name);

    /**
     * @param name The name of the user properties to get.
     * @return The values user property with the specified name.
     * @since 4.0.0
     */
    @Immutable
    @NotNull List<@NotNull KafkaHeader> getAllForName(@NotNull String name);

}

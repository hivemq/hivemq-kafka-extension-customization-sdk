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

import com.hivemq.extension.sdk.api.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Optional;

/**
 * @author Christoph Schäbel
 */
public interface KafkaRecord {

    @NotNull String getTopic();

    @NotNull KafkaHeaders getHeaders();

    @NotNull Optional<ByteBuffer> getKey();

    @NotNull Optional<byte[]> getKeyAsByteArray();

    @NotNull Optional<ByteBuffer> getValue();

    @NotNull Optional<byte[]> getValueAsByteArray();

    @NotNull Optional<Long> getTimestamp();

    @NotNull Optional<Integer> getPartition();

    @NotNull Optional<Long> getOffset();
}

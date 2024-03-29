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

import java.nio.ByteBuffer;
import java.util.Optional;

/**
 * Represents a Kafka record, that was either read from or should be written to a Kafka cluster.
 * <p>
 * The internal state of this interface is completely immutable. All returned {@link ByteBuffer}s are read only and a
 * deep copy of any {@code byte[]} is made for every method call returning one.
 *
 * @author Christoph Schäbel
 * @author Georg Held
 * @since 4.4.0
 */
@Immutable
@DoNotImplement
public interface KafkaRecord {

    /**
     * @return The topic of this record.
     * @since 4.4.0
     */
    @NotNull String getTopic();

    /**
     * @return The {@link KafkaHeaders} of this record. These might be empty.
     * @since 4.4.0
     */
    @NotNull KafkaHeaders getHeaders();

    /**
     * @return An {@link Optional} of the key of this record.
     * @since 4.4.0
     */
    @NotNull Optional<ByteBuffer> getKey();

    /**
     * @return An {@link Optional} of the key of this record.
     * @since 4.4.0
     */
    @NotNull Optional<byte[]> getKeyAsByteArray();

    /**
     * @return An {@link Optional} of the value of this record.
     * @since 4.4.0
     */
    @NotNull Optional<ByteBuffer> getValue();

    /**
     * @return An {@link Optional} of the value of this record.
     * @since 4.4.0
     */
    @NotNull Optional<byte[]> getValueAsByteArray();

    /**
     * @return An {@link Optional} of the timestamp of this record, in milliseconds since UNIX epoch.
     * @since 4.4.0
     */
    @NotNull Optional<Long> getTimestamp();

    /**
     * @return An {@link Optional} of the partition number of this record.
     * @since 4.4.0
     */
    @NotNull Optional<Integer> getPartition();

    /**
     * @return An {@link Optional} of the offset value of this record.
     * @since 4.4.0
     */
    @NotNull Optional<Long> getOffset();
}

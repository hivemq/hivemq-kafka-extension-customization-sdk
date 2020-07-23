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
import java.nio.charset.Charset;

/**
 * * Represents the header of a Kafka record, that was either read from or should be written to a Kafka cluster.
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
public interface KafkaHeader {

    /**
     * @return the key of this header.
     * @since 4.4.0
     */
    @NotNull String getKey();

    /**
     * @return the value of this header.
     * @since 4.4.0
     */
    @NotNull ByteBuffer getValue();

    /**
     * @return the value of this header.
     * @since 4.4.0
     */
    @NotNull byte[] getValueAsByteArray();

    /**
     * @return the value of this header as a string. {@link java.nio.charset.StandardCharsets#UTF_8} is used for the
     *         decoding.
     * @since 4.4.0
     */
    @NotNull String getValueAsString();

    /**
     * @param charset the {@link Charset} that is used for the decoding.
     * @return the value of this header as a string.
     * @since 4.4.0
     */
    @NotNull String getValueAsString(@NotNull Charset charset);
}

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

package com.hivemq.extensions.kafka.api.builders;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.kafka.api.model.KafkaRecord;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * The KafkaRecordBuilder enables the creation of {@link KafkaRecord}s via its fluent API.
 * <p>
 * All data in a {@link KafkaRecord} except the topic is optional. Ensure that you set a topic via the {@link
 * KafkaRecordBuilder#topic(String)} method before you call {@link KafkaRecordBuilder#build()}.
 * <p>
 * The internal state of this interface can only be changed via its methods. All arguments, that have mutable data
 * types, are deep copied before the setting method returns.
 *
 * @author Christoph Schäbel
 * @author Georg Held
 * @since 4.4.0
 */
@DoNotImplement
public interface KafkaRecordBuilder {

    /**
     * Set the topic of the Kafka record.
     * <p>
     * This is required to successfully build a {@link KafkaRecord}.
     *
     * @param topic the name of the topic.
     * @return this builder
     * @throws IllegalArgumentException if topic is not a valid name for a Kafka topic
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder topic(@NotNull String topic);

    /**
     * Set the key of the Kafka record.
     *
     * @param key the value of the key.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder key(@NotNull ByteBuffer key);

    /**
     * Set the key of the Kafka record.
     *
     * @param key the value of the key.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder key(byte @NotNull [] key);

    /**
     * Set the key of the Kafka record.
     *
     * @param key the value of the key. {@link java.nio.charset.StandardCharsets#UTF_8} is used for encoding.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder key(@NotNull String key);

    /**
     * Set the key of the Kafka record.
     *
     * @param key     the value of the key.
     * @param charset the {@link Charset} used for encoding.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder key(@NotNull String key, @NotNull Charset charset);

    /**
     * Set the value of the Kafka record.
     *
     * @param value the value of the Kafka value.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder value(@NotNull ByteBuffer value);

    /**
     * Set the value of the Kafka record.
     *
     * @param value the value of the Kafka value.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder value(byte @NotNull [] value);

    /**
     * Set the value of the Kafka record.
     *
     * @param value the value of the Kafka value. {@link java.nio.charset.StandardCharsets#UTF_8} is used for encoding.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder value(@NotNull String value);

    /**
     * Set the value of the Kafka record.
     *
     * @param value   the value of the Kafka value.
     * @param charset the {@link Charset} used for encoding.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder value(@NotNull String value, @NotNull Charset charset);

    /**
     * Add a header to the Kafka record.
     *
     * @param key   the key of the header.
     * @param value the value of the header.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder header(@NotNull String key, @NotNull ByteBuffer value);

    /**
     * Add a header to the Kafka record.
     *
     * @param key   the key of the header.
     * @param value the value of the header.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder header(@NotNull String key, byte @NotNull [] value);

    /**
     * Add a header to the Kafka record.
     *
     * @param key   the key of the header.
     * @param value the value of the header. {@link java.nio.charset.StandardCharsets#UTF_8} is used for encoding.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder header(@NotNull String key, @NotNull String value);

    /**
     * Add a header to the Kafka record.
     *
     * @param key     the key of the header.
     * @param value   the value of the header.
     * @param charset the {@link Charset} used for encoding the {@code value}.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder header(@NotNull String key, @NotNull String value, @NotNull Charset charset);

    /**
     * Set the timestamp of the Kafka record.
     *
     * @param timestamp the value of the Kafka timestamp in milliseconds since UNIX epoch.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder timestamp(long timestamp);

    /**
     * Set the partition number of the Kafka record.
     *
     * @param partition the value of the Kafka partition number.
     * @return this builder
     * @since 4.4.0
     */
    @NotNull KafkaRecordBuilder partition(int partition);

    /**
     * Create a new {@link KafkaRecord} from the current state of this builder. The builder can be reused afterwards.
     *
     * @return a new {@link KafkaRecord} containing a snapshot of the current state of this builder.
     * @throws NullPointerException if the topic was not set.
     * @since 4.4.0
     */
    @NotNull KafkaRecord build();
}

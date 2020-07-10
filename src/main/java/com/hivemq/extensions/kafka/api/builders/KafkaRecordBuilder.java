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
package com.hivemq.extensions.kafka.api.builders;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extensions.kafka.api.model.KafkaRecord;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author Christoph Schäbel
 */
public interface KafkaRecordBuilder {

    @NotNull KafkaRecordBuilder topic(@NotNull String topic);

    @NotNull KafkaRecordBuilder header(@NotNull String key, @NotNull ByteBuffer value);

    @NotNull KafkaRecordBuilder header(@NotNull String key, @NotNull byte[] value);

    @NotNull KafkaRecordBuilder header(@NotNull String key, @NotNull String value);

    @NotNull KafkaRecordBuilder header(@NotNull String key, @NotNull String value, @NotNull Charset charset);

    @NotNull KafkaRecordBuilder key(@NotNull ByteBuffer key);

    @NotNull KafkaRecordBuilder key(@NotNull byte[] key);

    @NotNull KafkaRecordBuilder key(@NotNull String key);

    @NotNull KafkaRecordBuilder key(@NotNull String key, @NotNull Charset charset);

    @NotNull KafkaRecordBuilder value(@NotNull ByteBuffer value);

    @NotNull KafkaRecordBuilder value(@NotNull byte[] value);

    @NotNull KafkaRecordBuilder value(@NotNull String value);

    @NotNull KafkaRecordBuilder value(@NotNull String value, @NotNull Charset charset);

    @NotNull KafkaRecordBuilder timestamp(long timestamp);

    @NotNull KafkaRecordBuilder partition(int partition);

    @NotNull KafkaRecord build();
}

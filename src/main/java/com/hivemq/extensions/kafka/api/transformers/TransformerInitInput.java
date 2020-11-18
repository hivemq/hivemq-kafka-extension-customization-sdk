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

import com.codahale.metrics.MetricRegistry;
import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;

/**
 * A marker interface for the input object of the {@link Transformer#init(TransformerInitInput)} method.
 *
 * @author Christoph Schäbel
 * @author Georg Held
 * @since 4.4.0
 */
@Immutable
@DoNotImplement
public interface TransformerInitInput {

    /**
     * Get the {@link MetricRegistry} of this HiveMQ node. It is possible to add own metrics to monitor
     * custom business logic.
     *
     * @return the {@link MetricRegistry} of the HiveMQ node this Enterprise Extension for Kafka is running on.
     * @since 4.5.0
     */
    @NotNull MetricRegistry getMetricRegistry();

}

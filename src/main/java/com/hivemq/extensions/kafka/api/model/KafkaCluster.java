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

/**
 * This interface provides information about a {@code <kafka-cluster>} as it is configured in the {@code
 * kafka-extension.xml}.
 *
 * @author Christoph Schäbel
 * @author Georg Held
 * @since 4.4.0
 */
@Immutable
@DoNotImplement
public interface KafkaCluster {

    /**
     * @return the configured {@code <id>} of the cluster.
     * @since 4.4.0
     */
    @NotNull String getId();

    /**
     * @return the configured {@code <bootstrap-servers>} of the cluster.
     */
    @NotNull String getBootstrapServers();
}

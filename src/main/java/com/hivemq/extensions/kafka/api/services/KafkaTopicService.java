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

package com.hivemq.extensions.kafka.api.services;

import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;

import java.util.Map;
import java.util.Set;

/**
 * The KafkaTopicService enables the programmatic interaction with Kafka topics. All methods act on the Kafka cluster,
 * that the calling transformer is associated with.
 *
 * @author Georg Held
 * @see <a href=https://www.hivemq.com/docs/kafka/latest/enterprise-extension-for-kafka/kafka.html#kafka-clusters>Kafka
 *         Cluster configuration</a>.
 * @since 4.4.0
 */
@DoNotImplement
public interface KafkaTopicService {

    /**
     * Query the state of a single Kafka topic on the associated cluster.
     * <p>
     * This method can block the calling transformer.
     *
     * @param topic the name of a Kafka topic.
     * @return the {@link KafkaTopicState} of the topic. Possible values here are {@link KafkaTopicState#FAILURE},
     *         {@link KafkaTopicState#EXISTS} and {@link KafkaTopicState#MISSING}.
     * @since 4.4.0
     */
    @NotNull KafkaTopicState getKafkaTopicState(@NotNull String topic);

    /**
     * Query the states of multiple Kafka topics on the associated cluster.
     * <p>
     * The returned map contains exactly one entry per queried topic in the argument set.
     * <p>
     * This method can block the calling transformer.
     *
     * @param topics a set containing the names of Kafka topics.
     * @return a mapping of the queried Kafka topics to their {@link KafkaTopicState}. Possible values here are {@link
     *         KafkaTopicState#FAILURE}, {@link KafkaTopicState#EXISTS} and {@link KafkaTopicState#MISSING}.
     * @throws NullPointerException if {@code topics} is or contains null.
     * @since 4.4.0
     */
    @Immutable @NotNull Map<String, @NotNull KafkaTopicState> getKafkaTopicStates(@NotNull Set<@NotNull String> topics);

    /**
     * Create a single Kafka topic on the associated cluster. Use {@link KafkaTopicService#getKafkaTopicState(String)}
     * if you would like to check, whether the topic already exists.
     * <p>
     * This method can block the calling transformer.
     * <p>
     *
     * @param topic the name of a new Kafka topic.
     * @return the {@link KafkaTopicState} of the topic after this methods completes. Possible values here are {@link
     *         KafkaTopicState#FAILURE}, {@link KafkaTopicState#EXISTS} and {@link KafkaTopicState#CREATED}.
     * @since 4.4.0
     */
    @NotNull KafkaTopicState createKafkaTopic(@NotNull String topic);

    /**
     * Create multiple Kafka topics on the associated cluster. Use {@link KafkaTopicService#getKafkaTopicStates(Set)} if
     * you would like to check, whether the topics already exists.
     * <p>
     * * The returned map contains exactly one entry per queried topic in the argument set.
     * <p>
     * This method can block the calling transformer.
     *
     * @param topics a set containing the names of new Kafka topics.
     * @return a mapping of the Kafka topics to their {@link KafkaTopicState} after this method completes. Possible
     *         values here are {@link KafkaTopicState#FAILURE}, {@link KafkaTopicState#EXISTS} and {@link
     *         KafkaTopicState#CREATED}.
     * @throws NullPointerException if {@code topics} is or contains null.
     * @since 4.4.0
     */
    @NotNull Map<String, @NotNull KafkaTopicState> createKafkaTopics(@NotNull Set<@NotNull String> topics);

    /**
     * KafkaTopicState encodes the current known state of a Kafka topic on the associated cluster.
     *
     * @since 4.4.0
     */
    enum KafkaTopicState {
        /**
         * FAILURE signals that the operation for the topic was not successful. No possible information about the true
         * state of this topic on the Kafka cluster can be assumed.
         *
         * @since 4.4.0
         */
        FAILURE,
        /**
         * This topic is missing from the associated Kafka cluster and may need to be created before a record can be
         * published to it.
         *
         * @since 4.4.0
         */
        MISSING,
        /**
         * This topic already exists on the associated Kafka cluster.
         *
         * @since 4.4.0
         */
        EXISTS,
        /**
         * This topic was created on the associated Kafka cluster.
         *
         * @since 4.4.0
         */
        CREATED,
    }
}

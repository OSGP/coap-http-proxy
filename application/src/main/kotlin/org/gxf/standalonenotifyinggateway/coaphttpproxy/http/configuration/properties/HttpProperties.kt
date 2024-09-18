// SPDX-FileCopyrightText: Contributors to the GXF project
//
// SPDX-License-Identifier: Apache-2.0
package org.gxf.standalonenotifyinggateway.coaphttpproxy.http.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

import java.time.Duration

/**
 * @property url
 * @property sslBundle
 * @property connectionTimeout
 */
@ConfigurationProperties(prefix = "config.http")
data class HttpProperties(
    val url: String,
    val sslBundle: String?,
    val connectionTimeout: Duration
)


package com.solaria.persistence.security.service;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Propriedades do token de serviço (HMAC-SHA256), relacionamento M2M (/internal/**)
 */

// Habilita essa classe com as variaveis em application.properties com prefixo app.jwt.service
@ConfigurationProperties(prefix = "app.jwt.service")
public class ServiceTokenProperties {

    // Secret HMAC (SERVICE_JWT_SECRET) usado para assinar/validar os tokens de serviço
    private String secret;

    // Secret comparado no mint (SERVICE_CLIENT_SECRET)
    private String clientSecret;

    // Issuer gravado nos tokens de serviço emitidos por esse fluxo
    private String issuer;

    // TTL do access token de serviço
    private long accessTtlMs;

    // TTL do refresh token de serviço
    private long refreshTtlMs;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public long getAccessTtlMs() {
        return accessTtlMs;
    }

    public void setAccessTtlMs(long accessTtlMs) {
        this.accessTtlMs = accessTtlMs;
    }

    public long getRefreshTtlMs() {
        return refreshTtlMs;
    }

    public void setRefreshTtlMs(long refreshTtlMs) {
        this.refreshTtlMs = refreshTtlMs;
    }


    // Getter derivado, converte long para milisegundos
    public Duration getAccessTtl() {
        return Duration.ofMillis(accessTtlMs);
    }

    // Getter derivado, converte long para milisegundos
    public Duration getRefreshTtl() {
        return Duration.ofMillis(refreshTtlMs);
    }
}

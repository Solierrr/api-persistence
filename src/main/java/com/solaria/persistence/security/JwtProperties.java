package com.solaria.persistence.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Propriedades do JWT de usuário (resource server RS256 + JWKS), emitido pelo api-auth.
 */

// Habilita essa classe com as variaveis em application.properties com prefixo app.jwt
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {

    // URL do endpoint JWKS do api-auth
    private String jwkSetUri;

    // Issuer esperado no token de usuário
    private String issuer;

    public String getJwkSetUri() {
        return jwkSetUri;
    }

    public void setJwkSetUri(String jwkSetUri) {
        this.jwkSetUri = jwkSetUri;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}

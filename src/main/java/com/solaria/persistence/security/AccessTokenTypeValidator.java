package com.solaria.persistence.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;


/**
 * Recusa tokens que não são accesses tokens
 * sem isso refresh tokens podem ser usados como accesses tokens
 */

// Implementa OAuth2TokenValidator<Jwt> -> contrato exigido para ser usado em um DelegatingOAuth2TokenValidator 
public class AccessTokenTypeValidator implements OAuth2TokenValidator<Jwt> {


    private static final String CLAIM_TOKEN_TYPE = "token_type";

    private static final String EXPECTED_TOKEN_TYPE = "access";

    // Método exigido pela interface OAuth2TokenValidator<Jwt>
    // chamado automaticamente pelo DelegatingOAuth2TokenValidator para cada JWT decodificado com sucesso
    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        // Extrai o claim TOKEN_TYPE como String e valida se é igual o esperado
        String tokenType = token.getClaimAsString(CLAIM_TOKEN_TYPE);
        if (EXPECTED_TOKEN_TYPE.equals(tokenType)) {
            return OAuth2TokenValidatorResult.success();
        }

        // monta OAuth2Error com código 
        OAuth2Error error = new OAuth2Error(
                "invalid_token_type",
                "O claim 'token_type' precisa ser exatamente access (recebido: " + tokenType + ").",null);
        // Devolve a falha -> convertida para 401 via ProblemDetailAuthenticationEntryPoint
        return OAuth2TokenValidatorResult.failure(error);
    }
}

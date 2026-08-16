package com.solaria.persistence.security.service;

import java.nio.charset.StandardCharsets;

import java.security.Key;

import java.time.Duration;
import java.time.Instant;

import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.solaria.persistence.exception.ServiceAuthException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Emite e valida o token de serviço M2M (API para API) via JJWT + HMAC-SHA256
 * Usa JJWT em vez de Nimbus (Impede que um token M2M seja válidado como
 * accesses/refresh token)
 * Segredo e issuer são próprios deste fluxo M2M (/internal/**)
 */
@Component
public class ServiceTokenProvider {

    public static final String TOKEN_TYPE_ACCESS = "service_access";
    public static final String TOKEN_TYPE_REFRESH = "service_refresh";

    private static final String CLAIM_TOKEN_TYPE = "token_type";
    private static final String SUBJECT = "internal-client";
    private static final int MIN_SECRET_BYTES = 32;

    private final ServiceTokenProperties properties;
    private final Key signingKey;

    public ServiceTokenProvider(ServiceTokenProperties properties) {
        this.properties = properties;
        this.signingKey = buildSigningKey(properties.getSecret());
    }

    // Método responsável por validar a secret e criar uma chave criptográfica para
    // uso com algoritmo HMAC-SHA256/HS256 com os bytes da mesma
    private static Key buildSigningKey(String secret) {

        // Verificação de secret
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException(
                    "Secret precisa estar definido para o token de serviço M2M.");
        }

        // transforma secret em bytes
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);

        // Verificação de tamanho da secret
        if (secretBytes.length < MIN_SECRET_BYTES) {
            throw new IllegalStateException(
                    "Secret precisa ter pelo menos " + MIN_SECRET_BYTES + "bytes;");
        }

        // Cria uma chave criptográfica para uso com algoritmo HMAC-SHA256/HS256 usando
        // os bytes do secret
        return new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    // retorna um accesses token
    public String mintAccessToken() {
        return mint(TOKEN_TYPE_ACCESS, properties.getAccessTtl());
    }

    // retorna um refrsh token
    public String mintRefreshToken() {
        return mint(TOKEN_TYPE_REFRESH, properties.getRefreshTtl());
    }

    // cria um acesses/refrsh token 
    private String mint(String tokenType, Duration ttl) {
        Instant now = Instant.now(); // instante da criação do token
        return Jwts.builder()
                .setId(UUID.randomUUID().toString()) // gera um UUID único para o token
                .setIssuer(properties.getIssuer()) // define quem emitiu o token
                .setSubject(SUBJECT) // define "descrição/assunto" do token
                .claim(CLAIM_TOKEN_TYPE, tokenType) // claim personalizado, define o tipo do token accesses/refresh
                .setIssuedAt(Date.from(now)) // define o instante de criação do token
                .setExpiration(Date.from(now.plus(ttl))) // define instante de expiração do token
                .signWith(signingKey, SignatureAlgorithm.HS256) // assina o token com a secret + algoritmo HMAC-SHA256/HS256
                .compact(); // transforma o token em um jwt
    }

    // validação do token
    public Claims parseAndValidate(String token, String expectedTokenType) {
        Claims claims; // representa os dados dentro do payload
        try {
             // Cria um parser configurado para validar o JWT
            claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey) // define a chave usada para valiadar a assinatura
                    .requireIssuer(properties.getIssuer()) // exige um emissor especifico para o token
                    .build()  // constrói o parser com essas configurações
                    .parseClaimsJws(token) // valida o jwt
                    .getBody(); // retorna o paylaod do token
        } catch (JwtException | IllegalArgumentException ex) {
            throw new ServiceAuthException("Token de serviço inválido: " + ex.getMessage());
        }

        // verificação do tipo de token esperado e recebido
        String actualTokenType = claims.get(CLAIM_TOKEN_TYPE, String.class);
        if (!expectedTokenType.equals(actualTokenType)) {
            throw new ServiceAuthException(
                    "Tipo de token de serviço inesperado (esperado: " + expectedTokenType+ ", recebido: " + actualTokenType + ").");
        }
        return claims;
    }
}
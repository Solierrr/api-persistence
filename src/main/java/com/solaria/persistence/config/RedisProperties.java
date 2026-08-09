package com.solaria.persistence.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redis") // tudo que começar com redis no application.properties é mapeado aqui
public class RedisProperties {
    private String namespace = "core"; // valor padrão dos valores dos filtros, sempre vai começar com "core:"

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    
}
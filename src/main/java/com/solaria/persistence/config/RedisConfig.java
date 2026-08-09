package com.solaria.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration // Configura a infra do app, exige que seja criado quando o projeto subir.
public class RedisConfig {
    
    @Bean // O objeto será retornado e gerenciado pelo Spring (template), permite @Autowired.
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>(); // Cria o template, que é um cliente do Redis, responsável por se comunicar com ele.

        template.setConnectionFactory(connectionFactory); // Permite que o template abra conexões.

        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
                                                                // Os serializers serão responsáveis por transformar string > json > bytes, assim o Redis consegue interpretar a requisição.
        template.setValueSerializer(RedisSerializer.json());
        template.setHashValueSerializer(RedisSerializer.json());

        template.afterPropertiesSet(); // Diz ao Spring que a configuração foi completa e ele valida.

        return template; // Retorna o cliente Redis pronto para uso.
    }
}
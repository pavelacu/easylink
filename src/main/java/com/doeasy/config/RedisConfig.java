package com.doeasy.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() throws URISyntaxException{                
        String redisUrl = System.getenv("REDIS_URL");        
	    URI redistogoUri = new URI(redisUrl);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redistogoUri.getHost(), redistogoUri.getPort());
        System.out.println(redistogoUri.getUserInfo().split(":", 2)[1]);
        redisStandaloneConfiguration.setPassword(redistogoUri.getUserInfo().split(":", 2)[1]);              
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }
}
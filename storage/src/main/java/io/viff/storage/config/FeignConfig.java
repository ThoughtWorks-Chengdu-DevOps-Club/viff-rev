package io.viff.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static feign.Logger.Level;

@Configuration
public class FeignConfig {
    @Bean
    public Level feignLoggerLevel() {
        return Level.FULL;
    }
}

package com.huy8895.dictionaryapi.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public Gson setGson() {
        return new Gson();
    }
}

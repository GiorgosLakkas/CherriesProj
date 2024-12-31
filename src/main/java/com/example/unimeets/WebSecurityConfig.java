package com.example.unimeets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class WebSecurityConfig {

    @Bean
    public HandlerMappingIntrospector customMvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }
}
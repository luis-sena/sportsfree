package br.com.sportsfree.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:[*]", "https://front-bootcamp.herokuapp.com", "http://127.0.0.1:[*]")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE", "PATCH");
    }
}

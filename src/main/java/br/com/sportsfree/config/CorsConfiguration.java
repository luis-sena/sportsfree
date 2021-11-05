package br.com.sportsfree.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:[*]");

        registry.addMapping("/**")
                .allowedOriginPatterns("http://127.0.0.1:[*]");

        registry.addMapping("/**")
                .allowedOriginPatterns("https://front-bootcamp.herokuapp.com");
    }
}

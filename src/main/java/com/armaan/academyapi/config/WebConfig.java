package com.armaan.academyapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://suspectless-zaire-agitatorial.ngrok-free.dev")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders("*");
        WebMvcConfigurer.super.addCorsMappings(registry);
    }

}

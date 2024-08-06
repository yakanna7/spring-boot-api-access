package com.skytech.api_access.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfiguration {

    @Value("${api.student.service.url}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(baseUrl).build();
    }
}

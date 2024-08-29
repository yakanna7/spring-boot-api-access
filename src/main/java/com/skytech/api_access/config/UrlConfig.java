package com.skytech.api_access.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlConfig {
    // Injecting the base URL from application properties or environment
    @Value("${api.student.service.url}")
    private String baseUrl;


}

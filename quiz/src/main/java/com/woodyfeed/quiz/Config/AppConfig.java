package com.woodyfeed.quiz.Config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EnableAsync
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private List<String> allowedOrigins;
    private String applicationName;
    private String baseUrl;
    private String loginPageUrl;
    private String loginSuccessUrl;
}

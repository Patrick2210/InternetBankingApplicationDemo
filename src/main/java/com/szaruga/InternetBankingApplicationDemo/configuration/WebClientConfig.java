package com.szaruga.InternetBankingApplicationDemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for WebClient bean creation.
 */
@Configuration
public class WebClientConfig {

    /**
     * Creates and configures a WebClient bean.
     *
     * @return WebClient bean instance.
     */
    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}

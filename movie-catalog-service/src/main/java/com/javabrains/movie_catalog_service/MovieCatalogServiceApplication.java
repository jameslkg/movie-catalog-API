package com.javabrains.movie_catalog_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class MovieCatalogServiceApplication {

    // 1. Standard builder for internal frameworks (like Eureka Client)
    @Bean
    @Primary
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    // 2. Load-balanced builder for service-to-service calls
    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }

    // 3. Build actual client explicitly using the load-balanced variant
    @Bean
    public RestClient loadBalancedRestClient(@LoadBalanced RestClient.Builder lbBuilder) {
        return lbBuilder.build();
    }


	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}

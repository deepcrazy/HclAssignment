//package com.deepanshu.hclassignment.configuration;
//
//import io.github.resilience4j.circuitbreaker.CircuitBreaker;
//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.Duration;
//
//@Configuration
//public class CircuitBreakerConfiguration {
//
//    @Bean
//    public CircuitBreaker circuitBreaker() {
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(50) // Adjust the failure rate threshold as needed
//                .waitDurationInOpenState(Duration.ofMillis(10000)) // Adjust the wait duration in open state as needed
//                .build();
//
//        return CircuitBreaker.of("userServiceCircuitBreaker", circuitBreakerConfig);
//    }
//}
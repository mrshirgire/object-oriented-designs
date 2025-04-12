package com.ood.parkingLot.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;

import java.time.Duration;

public class Resilience4jConfig {

    private static CircuitBreaker circuitBreaker = null;
    private static Retry retry = null;

    static{

        circuitBreaker = CircuitBreaker.of("parkingLotCircuitBreaker", CircuitBreakerConfig.custom()
                .failureRateThreshold(50) // Break if 50% of calls fail
                .waitDurationInOpenState(Duration.ofSeconds(5)) // Wait before retrying
                .slidingWindowSize(10) // Evaluate 10 calls
                .build());

        retry = Retry.of("parkingLotRetry", RetryConfig.custom()
                .maxAttempts(3) // Retry up to 3 times
                .waitDuration(Duration.ofSeconds(1)) // Wait 1 second between retries
                .build());
    }


    public Retry getRetry() {
        return retry;
    }



}

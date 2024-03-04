package com.szaruga.InternetBankingApplicationDemo.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Component responsible for sending PESEL validation requests to an external application.
 */
@Component
public class PeselValidationRequestSender {

    private final WebClient webClient;

    /**
     * Constructs a PeselValidationRequestSender with the specified WebClient.
     *
     * @param webClient The WebClient used to send requests.
     */
    public PeselValidationRequestSender(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Sends a PESEL validation request to an external application.
     *
     * @param peselNumber The PESEL number to validate.
     * @return The response from the external application.
     */
    public ResponseEntity<String> sendPeselValidationRequest(String peselNumber) {
        // Base URL for the external application
        String baseUrl = "http://localhost:8082/api/verify-pesel";

        // Building the request and retrieving the response
        WebClient.ResponseSpec responseSpec = webClient
                .post()
                .uri(baseUrl)
                .body(BodyInserters.fromValue(peselNumber))
                .retrieve();

        // Blocking and extracting the response body
        String responseBody = responseSpec.bodyToMono(String.class).block();

        // Creating and returning a ResponseEntity with the response body
        return ResponseEntity.ok(responseBody);
    }
}

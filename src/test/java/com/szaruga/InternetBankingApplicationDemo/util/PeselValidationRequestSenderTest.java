package com.szaruga.InternetBankingApplicationDemo.util;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.*;

/**
 * Note: To pass these tests, an external application must be running to handle the requests sent by the PeselValidationRequestSender class.
 * The external application should be accessible at http://localhost:8082/api/verify-pesel.
 */
@ExtendWith(MockitoExtension.class)
public class PeselValidationRequestSenderTest {

    @BeforeEach
    public void setUp() {
        baseURI = "http://localhost:8082";
    }

    @Test
    public void testSendPeselValidationRequest_Response_200() {
        String validPeselNumber = "92121251654";

        given()
                .contentType(ContentType.JSON)
                .body(validPeselNumber)
                .when()
                .post("/api/verify-pesel")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testSendPeselValidationRequest_Response_400() {
        String invalidPeselNumber = "9212122251654";

        given()
                .contentType(ContentType.JSON)
                .body(invalidPeselNumber)
                .when()
                .post("/api/verify-pesel")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
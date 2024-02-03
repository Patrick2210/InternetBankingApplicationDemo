package com.szaruga.InternetBankingApplicationDemo.exception.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class IllegalSortingRequest extends RuntimeException {
    public IllegalSortingRequest(String message){
        super(message);
    }
}

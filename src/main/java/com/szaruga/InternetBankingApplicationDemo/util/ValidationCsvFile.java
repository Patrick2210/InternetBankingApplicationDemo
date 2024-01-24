package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;
import org.springframework.web.multipart.MultipartFile;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.CSV_FILE;
import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.MUST_BE_NOT_NULL;

public class ValidationCsvFile {
    //todo ogolnie musze wylapac z fileItem czy jest name if not exception oraz czy ten file ma jakis size czy jest 0
    public static void checkIfCsvFileIsNotNull(MultipartFile file) {
        if (file == null) {
            throw new ValidationException(CSV_FILE.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }
}

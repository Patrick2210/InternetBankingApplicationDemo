package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;
import org.springframework.web.multipart.MultipartFile;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.CSV_FILE;
import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.MUST_BE_NOT_NULL;
/**
 * Utility class for validating CSV files.
 */
public class ValidationCsvFile {
    //todo ogolnie musze wylapac z fileItem czy jest name if not exception oraz czy ten file ma jakis size czy jest 0
    /**
     * Checks if the provided CSV file is not null.
     *
     * @param file The CSV file to validate.
     * @throws ValidationException If the CSV file is null.
     */
    public static void checkIfCsvFileIsNotNull(MultipartFile file) {
        if (file == null) {
            throw new ValidationException(CSV_FILE.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }
}

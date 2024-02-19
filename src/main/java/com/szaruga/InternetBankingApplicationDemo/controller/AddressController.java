package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationCsvFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Controller class for managing address-related operations.
 */
@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;

    /**
     * Constructs an instance of the AddressController.
     *
     * @param addressService The service for managing addresses.
     */
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Endpoint to import addresses from a CSV file.
     *
     * @param file The CSV file containing addresses.
     * @return ResponseEntity indicating the result of the import operation.
     */
    @PostMapping("/addresses/import")
    public ResponseEntity<String> importCsvFile(@RequestParam("file") MultipartFile file) {
        ValidationCsvFile.checkIfCsvFileIsNotNull(file);
        addressService.importAddressesFromCsv(file);
        return ResponseEntity.ok(CSV_FILE.getMessage() + IMPORT_SUCCESS.getMessage());
    }
}
package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationCsvFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/addresses/import")
    public ResponseEntity<String> importCsvFile(@RequestParam("file") MultipartFile file) {
            ValidationCsvFile.checkIfCsvFileIsNotNull(file);
            addressService.importAddressesFromCsv(file);
            return ResponseEntity.ok(CSV_FILE.getMessage() + IMPORT_SUCCESS.getMessage());
    }
}
package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
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
    public ResponseEntity<String> importAddresses(@RequestParam("file") MultipartFile file) {
        try {
            addressService.importAddressesFromCsv(file);
            return ResponseEntity.ok(CSV_FILE.getMessage() + IMPORT_SUCCESS.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(IMPORT_ERROR.getMessage() + CSV_FILE.getMessage());
        }
    }
}

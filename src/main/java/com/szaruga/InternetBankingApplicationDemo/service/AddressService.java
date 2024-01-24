package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.exception.address.AddressCsvFileException;
import com.szaruga.InternetBankingApplicationDemo.jpa.AddressRepository;
import com.szaruga.InternetBankingApplicationDemo.util.ParseCsvFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ParseCsvFile parseCsvFile;

    @Autowired
    public AddressService(AddressRepository addressRepository, ParseCsvFile parseCsvFile) {
        this.addressRepository = addressRepository;
        this.parseCsvFile = parseCsvFile;
    }

    public void importAddressesFromCsv(MultipartFile file) {
        try {
            addressRepository.saveAll(parseCsvFile.csvFileIntoList(file));
        } catch (IOException e) {
            throw new AddressCsvFileException(IMPORT_ERROR.getMessage() + CSV_FILE.getMessage());
        }
    }
}
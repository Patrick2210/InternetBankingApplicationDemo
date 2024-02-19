package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.exception.address.CsvFileException;
import com.szaruga.InternetBankingApplicationDemo.jpa.AddressRepository;
import com.szaruga.InternetBankingApplicationDemo.util.ParseCsvFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Service class for managing addresses.
 */
@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ParseCsvFile parseCsvFile;

    /**
     * Constructs an instance of the AddressService.
     *
     * @param addressRepository The repository for managing address entities.
     * @param parseCsvFile      Utility class for parsing CSV files.
     */
    @Autowired
    public AddressService(AddressRepository addressRepository, ParseCsvFile parseCsvFile) {
        this.addressRepository = addressRepository;
        this.parseCsvFile = parseCsvFile;
    }

    /**
     * Imports addresses from a CSV file and saves them to the repository.
     *
     * @param file The CSV file containing addresses.
     * @throws CsvFileException If an error occurs while processing the CSV file.
     */
    public void importAddressesFromCsv(MultipartFile file) {
        try {
            addressRepository.saveAll(parseCsvFile.csvFileIntoList(file));
        } catch (IOException e) {
            throw new CsvFileException(IMPORT_ERROR.getMessage() + CSV_FILE.getMessage());
        }
    }

    /**
     * Checks if an address with the given address exists in the repository.
     *
     * @param address The address to check.
     * @return True if the address exists, false otherwise.
     */
    public boolean existsByAddress(String address) {
        return addressRepository.existsByAddress(address);
    }

    /**
     * Checks if an address with the given city exists in the repository.
     *
     * @param city The city to check.
     * @return True if the city exists, false otherwise.
     */
    public boolean existsByCity(String city) {
        return addressRepository.existsByCity(city);
    }

    /**
     * Checks if an address with the given postcode exists in the repository.
     *
     * @param postcode The postcode to check.
     * @return True if the postcode exists, false otherwise.
     */
    public boolean existsByPostcode(String postcode) {
        return addressRepository.existsByPostcode(postcode);
    }

    /**
     * Checks if an address with the given voivodeship exists in the repository.
     *
     * @param voivodeship The voivodeship to check.
     * @return True if the voivodeship exists, false otherwise.
     */
    public boolean existsByVoivodeship(String voivodeship) {
        return addressRepository.existsByVoivodeship(voivodeship);
    }

    /**
     * Checks if an address with the given county exists in the repository.
     *
     * @param county The county to check.
     * @return True if the county exists, false otherwise.
     */
    public boolean existsByCounty(String county) {
        return addressRepository.existsByCounty(county);
    }
}
package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.entity.AddressEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * Utility class for parsing CSV files into a list of AddressEntity objects.
 */
@Component
public class ParseCsvFile {
    /**
     * Parses a CSV file uploaded via MultipartFile into a list of AddressEntity objects.
     *
     * @param file The CSV file to parse.
     * @return A list of AddressEntity objects representing the data in the CSV file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<AddressEntity> csvFileIntoList(MultipartFile file) throws IOException {
        List<AddressEntity> addresses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            boolean isHeader = true;
            String line;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] values = line.split(";");
                AddressEntity address = new AddressEntity();
                address.setPostcode(values[0]);
                address.setAddress(values[1]);
                address.setCity(values[2]);
                address.setVoivodeship(values[3]);
                address.setCounty(values[4]);
                addresses.add(address);
            }
        }
        return addresses;
    }
}
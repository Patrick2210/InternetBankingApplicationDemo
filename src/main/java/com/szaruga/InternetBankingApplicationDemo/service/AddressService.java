package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.entity.AddressEntity;
import com.szaruga.InternetBankingApplicationDemo.jpa.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void importAddressesFromCsv(MultipartFile file) throws IOException {
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
            addressRepository.saveAll(addresses);
        }
    }
}
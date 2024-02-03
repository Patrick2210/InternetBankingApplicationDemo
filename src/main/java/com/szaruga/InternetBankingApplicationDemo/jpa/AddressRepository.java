package com.szaruga.InternetBankingApplicationDemo.jpa;

import com.szaruga.InternetBankingApplicationDemo.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    boolean existsByAddress(String address);

    boolean existsByCity(String city);

    boolean existsByPostcode(String postcode);

    boolean existsByVoivodeship(String voivodeship);

    boolean existsByCounty(String county);
}

package com.szaruga.InternetBankingApplicationDemo.jpa;

import com.szaruga.InternetBankingApplicationDemo.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing AddressEntity objects.
 */
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    /**
     * Checks if an address exists by the specified address.
     *
     * @param address The address to check.
     * @return True if an address with the specified address exists, false otherwise.
     */
    boolean existsByAddress(String address);

    /**
     * Checks if an address exists by the specified city.
     *
     * @param city The city to check.
     * @return True if an address with the specified city exists, false otherwise.
     */
    boolean existsByCity(String city);

    /**
     * Checks if an address exists by the specified postcode.
     *
     * @param postcode The postcode to check.
     * @return True if an address with the specified postcode exists, false otherwise.
     */
    boolean existsByPostcode(String postcode);

    /**
     * Checks if an address exists by the specified voivodeship.
     *
     * @param voivodeship The voivodeship to check.
     * @return True if an address with the specified voivodeship exists, false otherwise.
     */
    boolean existsByVoivodeship(String voivodeship);

    /**
     * Checks if an address exists by the specified county.
     *
     * @param county The county to check.
     * @return True if an address with the specified county exists, false otherwise.
     */
    boolean existsByCounty(String county);
}

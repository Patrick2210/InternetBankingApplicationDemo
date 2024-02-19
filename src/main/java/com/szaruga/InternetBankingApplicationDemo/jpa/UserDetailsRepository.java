package com.szaruga.InternetBankingApplicationDemo.jpa;

import com.szaruga.InternetBankingApplicationDemo.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing UserDetailsEntity objects.
 */
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {
}

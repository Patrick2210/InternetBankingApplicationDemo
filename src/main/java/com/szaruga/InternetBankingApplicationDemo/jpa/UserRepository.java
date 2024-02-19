package com.szaruga.InternetBankingApplicationDemo.jpa;

import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing UserEntity objects.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
package com.szaruga.InternetBankingApplicationDemo.jpa;

import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
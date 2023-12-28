package com.szaruga.InternetBankingApplicationDemo.jpa;

import com.szaruga.InternetBankingApplicationDemo.dto.UserDto;
import com.szaruga.InternetBankingApplicationDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

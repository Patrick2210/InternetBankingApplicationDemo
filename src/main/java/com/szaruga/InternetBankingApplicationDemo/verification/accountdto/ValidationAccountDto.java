package com.szaruga.InternetBankingApplicationDemo.verification.accountdto;

import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountDto;
import org.springframework.stereotype.Component;

@Component
public class ValidationAccountDto {
    public void validateDto(AccountDto accountDto){
        FiledCheckAccountType.validate(accountDto.getAccountType());
    }
}

package com.szaruga.InternetBankingApplicationDemo.verification.accountdto;

import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountDto;
import org.springframework.stereotype.Component;

/**
 * Utility class for validating AccountDto objects.
 */
@Component
public class ValidationAccountDto {
    /**
     * Validates the provided AccountDto object.
     *
     * @param accountDto The AccountDto object to validate.
     */
    public void validateDto(AccountDto accountDto) {
        FiledCheckAccountType.validate(accountDto.getAccountType());
    }
}
package com.szaruga.InternetBankingApplicationDemo.mapper;

import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountsPageDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.GetAccountsByIdDto;
import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;

/**
 * Mapper class for converting between AccountDto and AccountEntity objects.
 */
public class AccountMapper {
    /**
     * Converts an AccountDto to an AccountEntity.
     *
     * @param dto The AccountDto to convert.
     * @return The corresponding AccountEntity.
     */
    public static AccountEntity toEntity(AccountDto dto) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountType(dto.getAccountType());
        accountEntity.setBalance(dto.getBalance());
        accountEntity.setReferenceAccountNumber(dto.getReferenceAccountNumber());
        accountEntity.setUser(dto.getUser());
        return accountEntity;
    }

    /**
     * Maps an AccountEntity to an AccountsPageDto.
     *
     * @param accountEntity The AccountEntity to map.
     * @return The corresponding AccountsPageDto.
     */
    public static AccountsPageDto mapAccountsEntityToPageDto(AccountEntity accountEntity) {
        AccountsPageDto accountsPageDto = new AccountsPageDto();
        accountsPageDto.setId(accountEntity.getId());
        accountsPageDto.setReferenceAccountNumber(accountEntity.getReferenceAccountNumber());
        return accountsPageDto;
    }

    /**
     * Maps an AccountEntity to a GetAccountsByIdDto.
     *
     * @param accountEntity The AccountEntity to map.
     * @return The corresponding GetAccountsByIdDto.
     */
    public static GetAccountsByIdDto mapAccountEntityToGetAccountsByIdDto(AccountEntity accountEntity) {
        GetAccountsByIdDto accountsByIdDto = new GetAccountsByIdDto();
        accountsByIdDto.setAccountType(accountEntity.getAccountType());
        accountsByIdDto.setReferenceAccountNumber(accountEntity.getReferenceAccountNumber());
        accountsByIdDto.setBalance(accountEntity.getBalance());
        return accountsByIdDto;
    }
}
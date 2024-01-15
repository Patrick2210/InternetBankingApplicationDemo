package com.szaruga.InternetBankingApplicationDemo.mapper;

import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountsPageDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.GetAccountsByIdDto;
import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {
    public static AccountEntity toEntity(AccountDto dto) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountType(dto.getAccountType());
        accountEntity.setBalance(dto.getBalance());
        accountEntity.setReferenceAccountNumber(dto.getReferenceAccountNumber());
        accountEntity.setUser(dto.getUser());
        return accountEntity;
    }

    public static AccountsPageDto mapAccountsEntityToPaginationDto(AccountEntity accountEntity) {
        AccountsPageDto accountsPageDto = new AccountsPageDto();
        accountsPageDto.setReferenceAccountNumber(accountEntity.getReferenceAccountNumber());
        return accountsPageDto;
    }

    public static List<AccountsPageDto> mapAccountsEntitiesToPageDtoList(List<AccountEntity> content) {
        return content.stream()
                .map(AccountMapper::mapAccountsEntityToPaginationDto)
                .collect(Collectors.toList());
    }

    public static GetAccountsByIdDto mapAccountEntityToGetAccountsByIdDto(AccountEntity accountEntity) {
        GetAccountsByIdDto accountsByIdDto = new GetAccountsByIdDto();
        accountsByIdDto.setAccountType(accountEntity.getAccountType());
        accountsByIdDto.setReferenceAccountNumber(accountEntity.getReferenceAccountNumber());
        accountsByIdDto.setBalance(accountEntity.getBalance());
        return accountsByIdDto;
    }
}

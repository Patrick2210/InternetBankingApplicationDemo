package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.account.GetAccountsByIdDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountsPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.account.AccountNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import com.szaruga.InternetBankingApplicationDemo.mapper.AccountMapper;
import com.szaruga.InternetBankingApplicationDemo.util.AccountUtils;
import com.szaruga.InternetBankingApplicationDemo.util.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.ACCOUNT_NOT_FOUND_WITH_ID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountUtils accountUtils;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountUtils accountUtils) {
        this.accountRepository = accountRepository;
        this.accountUtils = accountUtils;
    }

    public Page<AccountsPageDto> getAccountsPagination(int pageNumber, int pageSize, String sort) {
        Pageable pageable = PageableUtils.buildPageable(pageNumber, pageSize, sort);
        Page<AccountEntity> accountsDtoPage = accountRepository.findAll(pageable);
        List<AccountsPageDto> accountsPageDtoList = AccountMapper.mapAccountsEntitiesToPageDtoList(accountsDtoPage.getContent());

        return new PageImpl<>(accountsPageDtoList, pageable, accountsDtoPage.getTotalElements());
    }

    public GetAccountsByIdDto getAccountById(int id) {
        AccountEntity account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_WITH_ID.getMessage() + id));
        return AccountMapper.mapAccountEntityToGetAccountsByIdDto(account);
    }

    public AccountEntity saveAccount(AccountEntity accountEntity) {
        accountEntity.setBalance(BigDecimal.ZERO);
        accountEntity.setReferenceAccountNumber(accountUtils.generateReferenceAccountNumber());
        return accountRepository.save(accountEntity);
    }

    public void deleteAccount(int id) {
        //todo zanim sie usunie trzeba sprawdzic czy jest balance == 0
        Optional<AccountEntity> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            accountRepository.deleteById(id);
        } else {
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_WITH_ID.getMessage() + id);
        }
    }

    //todo zrobic methode na update balanu
}

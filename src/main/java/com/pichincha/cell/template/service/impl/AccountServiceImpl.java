package com.pichincha.cell.template.service.impl;

import com.pichincha.cell.template.domain.Account;
import com.pichincha.cell.template.domain.dto.AccountDto;
import com.pichincha.cell.template.repository.AccountRepository;
import com.pichincha.cell.template.service.AccountService;
import com.pichincha.cell.template.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountDto createAccount(AccountDto data) {
        Account account = Mapper.modelMapper().map(data, Account.class);
        return Mapper.modelMapper().map(accountRepository.save(account), AccountDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountDto updateAccount(Long id, AccountDto data) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            resourceNotFound(id);
        }
        accountOptional.get().setAmount(data.getAmount());
        accountOptional.get().setAccountNumber(data.getAccountNumber());
        return Mapper.modelMapper().map(accountRepository.save(accountOptional.get()), AccountDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountDto getAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            resourceNotFound(id);
        }
        return Mapper.modelMapper().map(accountOptional.get(), AccountDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountDto> getAllAccounts() {
        List<AccountDto> list = new ArrayList<>();
        for (Account element : accountRepository.findAll()) {
            list.add(Mapper.modelMapper().map(element, AccountDto.class));
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long deleteAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            resourceNotFound(id);
        }
        accountRepository.deleteById(id);
        return id;
    }

    private void resourceNotFound(Long id) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Account with id %d not found", id));
    }
}

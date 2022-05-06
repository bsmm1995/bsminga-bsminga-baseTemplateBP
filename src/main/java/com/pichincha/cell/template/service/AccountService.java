package com.pichincha.cell.template.service;

import com.pichincha.cell.template.domain.dto.AccountDto;

import java.util.List;

public interface AccountService {
    /**
     * Save an account
     *
     * @param data Object containing the new account information
     * @return New account created
     */
    AccountDto createAccount(AccountDto data);

    /**
     * Update an account
     *
     * @param id   ID of the client to be upgraded
     * @param data Object containing account information to be updated
     * @return Account updated
     */
    AccountDto updateAccount(Long id, AccountDto data);

    /**
     * Find an account by ID
     *
     * @param id ID of the account to be searched
     * @return Account found
     */
    AccountDto getAccountById(Long id);

    /**
     * Get all accounts
     *
     * @return All accounts found
     */
    List<AccountDto> getAllAccounts();

    /**
     * Delete an account
     *
     * @param id Account ID to be deleted
     * @return ID of the customer that was deleted
     */
    Long deleteAccountById(Long id);
}

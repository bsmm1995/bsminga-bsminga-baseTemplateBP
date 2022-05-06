package com.pichincha.cell.template.controller;

import com.pichincha.cell.template.domain.base.ResponseDto;
import com.pichincha.cell.template.domain.dto.AccountDto;
import com.pichincha.cell.template.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints that manage the accounts
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;

    /**
     * Find an account by ID
     *
     * @param id ID of the account to be searched
     * @return Account found
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> getAccount(@PathVariable("id") Long id) {
        log.info("Endpoint to get an account by ID: id=" + id);
        return new ResponseEntity<>(new ResponseDto(accountService.getAccountById(id), "Record found"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Get all accounts
     *
     * @return All accounts found
     */
    @GetMapping()
    public ResponseEntity<ResponseDto> getAllAccounts() {
        log.info("Endpoint to get all accounts");
        List<AccountDto> result = accountService.getAllAccounts();
        return new ResponseEntity<>(new ResponseDto(result, String.format("%d Records found", result.size())), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Save an account
     *
     * @param dto Object containing the new account information
     * @return New account created
     */
    @PostMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<ResponseDto> saveAccount(@RequestBody AccountDto dto) {
        log.info("Endpoint to save an account: data=" + dto);
        return new ResponseEntity<>(new ResponseDto(accountService.createAccount(dto)), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Update an account
     *
     * @param id  ID of the client to be upgraded
     * @param dto Object containing account information to be updated
     * @return Account updated
     */
    @PutMapping(value = "/{id}", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<ResponseDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto dto) {
        log.info("Endpoint to update an account: id=" + id + ", data= " + dto);
        return new ResponseEntity<>(new ResponseDto(accountService.updateAccount(id, dto)), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete an account
     *
     * @param id Account ID to be deleted
     * @return ID of the customer that was deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCustomer(@PathVariable Long id) {
        log.info("Endpoint to delete an account: id = " + id);
        return new ResponseEntity<>(new ResponseDto(accountService.deleteAccountById(id)), new HttpHeaders(), HttpStatus.OK);
    }
}

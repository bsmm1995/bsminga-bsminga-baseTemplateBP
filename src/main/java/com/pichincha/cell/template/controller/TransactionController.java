package com.pichincha.cell.template.controller;

import com.pichincha.cell.template.domain.base.ResponseDto;
import com.pichincha.cell.template.domain.dto.TransactionDto;
import com.pichincha.cell.template.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Apis that manage the transactions
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * Find a Transaction by ID
     *
     * @param id ID of the transaction to be searched
     * @return Transaction found
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> getTransaction(@PathVariable("id") Long id) {
        log.info("Endpoint to get a transaction by ID: id=" + id);
        return new ResponseEntity<>(new ResponseDto(transactionService.getTransactionById(id), "Record found"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Find all transactions
     *
     * @return All transactions found
     */
    @GetMapping()
    public ResponseEntity<ResponseDto> getAllTransactions() {
        log.info("Endpoint to get all transactions");
        List<TransactionDto> result = transactionService.getAllTransactions();
        return new ResponseEntity<>(new ResponseDto(result, String.format("%d Records found", result.size())), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Create a Transaction
     *
     * @param dto Object containing the new transaction information.
     * @return New transaction created
     */
    @PostMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<ResponseDto> createTransaction(@RequestBody TransactionDto dto) {
        log.info("Endpoint to create a transaction: data=" + dto);
        return new ResponseEntity<>(new ResponseDto(transactionService.createTransaction(dto), "Successful API saveTransaction"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Update a Transaction
     *
     * @param id  ID of the transaction to be updated
     * @param dto Object containing the information of the transaction to be updated.
     * @return Transaction updated
     */
    @PutMapping(value = "/{id}", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<ResponseDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto dto) {
        log.info("Endpoint to update a transaction: data=" + dto);
        return new ResponseEntity<>(new ResponseDto(transactionService.updateTransaction(id, dto), "Successful API saveTransaction"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete a Transaction
     *
     * @param id ID of the transaction to be deleted
     * @return ID of the transaction that was deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteTransaction(@PathVariable("id") Long id) {
        log.info("Endpoint to delete a transaction: id=" + id);
        return new ResponseEntity<>(new ResponseDto(transactionService.deleteTransactionById(id), "Successful API deleteTransaction"), new HttpHeaders(), HttpStatus.OK);
    }
}

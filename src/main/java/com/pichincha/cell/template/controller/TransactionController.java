package com.pichincha.cell.template.controller;

import com.pichincha.cell.template.domain.dto.TransactionDto;
import com.pichincha.cell.template.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable("id") Long id) {
        log.info("Endpoint to get a transaction by ID: id=" + id);
        return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
    }

    /**
     * Find all transactions
     *
     * @return All transactions found
     */
    @GetMapping()
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        log.info("Endpoint to get all transactions");
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    /**
     * Create a Transaction
     *
     * @param dto Object containing the new transaction information.
     * @return New transaction created
     */
    @PostMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto dto) {
        log.info("Endpoint to create a transaction: data=" + dto);
        return new ResponseEntity<>(transactionService.createTransaction(dto), HttpStatus.OK);
    }

    /**
     * Update a Transaction
     *
     * @param id  ID of the transaction to be updated
     * @param dto Object containing the information of the transaction to be updated.
     * @return Transaction updated
     */
    @PutMapping(value = "/{id}", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto dto) {
        log.info("Endpoint to update a transaction: data=" + dto);
        return new ResponseEntity<>(transactionService.updateTransaction(id, dto), HttpStatus.OK);
    }

    /**
     * Delete a Transaction
     *
     * @param id ID of the transaction to be deleted
     * @return ID of the transaction that was deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteTransaction(@PathVariable("id") Long id) {
        log.info("Endpoint to delete a transaction: id=" + id);
        return new ResponseEntity<>(transactionService.deleteTransactionById(id), HttpStatus.OK);
    }
}

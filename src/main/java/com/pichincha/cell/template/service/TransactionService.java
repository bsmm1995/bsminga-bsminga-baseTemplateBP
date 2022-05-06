package com.pichincha.cell.template.service;

import com.pichincha.cell.template.domain.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    /**
     * Create a Transaction
     *
     * @param data Object containing the new transaction information.
     * @return New transaction created
     */
    TransactionDto createTransaction(TransactionDto data);

    /**
     * Update a Transaction
     *
     * @param id   ID of the transaction to be updated
     * @param data Object containing the information of the transaction to be updated.
     * @return Transaction updated
     */
    TransactionDto updateTransaction(Long id, TransactionDto data);

    /**
     * Find a Transaction by ID
     *
     * @param id ID of the transaction to be searched
     * @return Transaction found
     */
    TransactionDto getTransactionById(Long id);

    /**
     * Find all transactions
     *
     * @return All transactions found
     */
    List<TransactionDto> getAllTransactions();

    /**
     * Delete a Transaction
     *
     * @param id ID of the transaction to be deleted
     * @return ID of the transaction that was deleted
     */
    Long deleteTransactionById(Long id);
}

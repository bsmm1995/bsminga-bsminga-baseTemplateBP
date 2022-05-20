package com.pichincha.cell.template.service.impl;

import com.pichincha.cell.template.domain.Transaction;
import com.pichincha.cell.template.domain.dto.TransactionDto;
import com.pichincha.cell.template.repository.TransactionRepository;
import com.pichincha.cell.template.service.TransactionService;
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
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionDto createTransaction(TransactionDto data) {
        Transaction transaction = Mapper.modelMapper().map(data, Transaction.class);
        return Mapper.modelMapper().map(transactionRepository.save(transaction), TransactionDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionDto updateTransaction(Long id, TransactionDto data) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isEmpty()) {
            resourceNotFound(id);
        }
        transactionOptional.get().setAmount(data.getAmount());
        transactionOptional.get().setDescription(data.getDescription());
        transactionOptional.get().setCurrency(data.getCurrency());
        return Mapper.modelMapper().map(transactionRepository.save(transactionOptional.get()), TransactionDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionDto getTransactionById(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isEmpty()) {
            resourceNotFound(id);
        }
        return Mapper.modelMapper().map(transactionOptional.get(), TransactionDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TransactionDto> getAllTransactions() {
        List<TransactionDto> list = new ArrayList<>();
        for (Transaction element : transactionRepository.findAll()) {
            list.add(Mapper.modelMapper().map(element, TransactionDto.class));
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long deleteTransactionById(Long id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isEmpty()) {
            resourceNotFound(id);
        }
        transactionRepository.deleteById(id);
        return id;
    }

    private void resourceNotFound(Long id) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Transaction with id %d not found", id));
    }
}

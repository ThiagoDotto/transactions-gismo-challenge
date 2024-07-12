package com.pismo.service;

import com.pismo.resource.dto.TransactionDTO;
import com.pismo.model.Account;
import com.pismo.model.Operation;
import com.pismo.model.Transaction;
import com.pismo.service.repository.AccountRepository;
import com.pismo.service.repository.OperationRepository;
import com.pismo.service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository,
                               OperationRepository operationRepository) {

        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @Transactional
    public void save(TransactionDTO transactionDTO) {
        Optional<Account> optionalAccount = accountRepository.findById(transactionDTO.accountId());
        Optional<Operation> operationOptional = operationRepository.findById(transactionDTO.operationId());

        if (optionalAccount.isPresent() && operationOptional.isPresent()) {
            Account account = optionalAccount.get();
            Operation operation = operationOptional.get();

            Transaction transactionEntity = new Transaction();
            transactionEntity.setAccount(account);
            transactionEntity.setOperation(operation);
            transactionEntity.setAmount(CalculateSignValueService
                    .exec(transactionEntity.getOperation(), transactionDTO.amount()));

            transactionRepository.save(transactionEntity);
        }
    }
}

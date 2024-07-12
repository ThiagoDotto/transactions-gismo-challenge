package com.pismo.service;

import com.pismo.resource.dto.TransactionDTO;
import com.pismo.model.Account;
import com.pismo.model.Operation;
import com.pismo.model.Transaction;
import com.pismo.resource.exception.BusinessError;
import com.pismo.resource.exception.PismoException;
import com.pismo.service.repository.AccountRepository;
import com.pismo.service.repository.OperationRepository;
import com.pismo.service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    @Transactional
    public void save(TransactionDTO transactionDTO) throws PismoException {

        Account account = accountRepository.findById(transactionDTO.accountId()).orElseThrow(
                () -> new PismoException(BusinessError.CONTA_NAO_ENCONTRADA));

        Operation operation = operationRepository.findById(transactionDTO.operationId())
                .orElseThrow(() -> new PismoException(BusinessError.OPERACAO_NAO_ENCONTRADA));

        Transaction transactionEntity = new Transaction();
        transactionEntity.setAccount(account);
        transactionEntity.setOperation(operation);
        transactionEntity.setAmount(CalculateSignValueService
                .exec(transactionEntity.getOperation(), transactionDTO.amount()));

        transactionRepository.save(transactionEntity);
    }
}

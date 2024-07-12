package com.pismo.service;

import com.pismo.resource.dto.TransactionDTO;
import com.pismo.model.Account;
import com.pismo.model.Operation;
import com.pismo.model.Transaction;
import com.pismo.service.repository.AccountRepository;
import com.pismo.service.repository.OperationRepository;
import com.pismo.service.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;
    @Mock
    AccountRepository accountRepository;

    @Mock
    OperationRepository operationRepository;

    @InjectMocks
    TransactionService transactionService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveNewTransaction() {

        Account account = new Account();
        account.setId(10);
        account.setDocumentNumber(987654321L);

        TransactionDTO transactionDTO = new TransactionDTO(2,
                1,
                new BigDecimal("520.25"));

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("520.25"));

        when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        when(operationRepository.findById(any())).thenReturn(Optional.of(new Operation()));

        transactionService.save(transactionDTO);

        verify(accountRepository, times(1)).findById(anyInt());
        verify(transactionRepository, times(1)).save(any());
        verify(operationRepository, times(1)).findById(any());

    }
}
package com.pismo.service;

import com.pismo.resource.dto.TransactionDTO;
import com.pismo.model.Account;
import com.pismo.model.Operation;
import com.pismo.model.Transaction;
import com.pismo.resource.exception.BusinessError;
import com.pismo.resource.exception.PismoException;
import com.pismo.repository.AccountRepository;
import com.pismo.repository.OperationRepository;
import com.pismo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ActiveProfiles("test")
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
    void shouldSaveNewTransaction() throws PismoException {

        Account account = new Account();
        account.setId(10);
        account.setDocumentNumber(987654321L);

        TransactionDTO transactionDTO = new TransactionDTO(2,
                1,
                new BigDecimal("520.25"));

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("520.25"));

        Operation operation = new Operation();
        operation.setOperationType("PAGAMENTO");
        operation.setId(1);

        when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        when(operationRepository.findById(any())).thenReturn(Optional.of(operation));

        transactionService.save(transactionDTO);

        verify(accountRepository, times(1)).findById(anyInt());
        verify(transactionRepository, times(1)).save(any());
        verify(operationRepository, times(1)).findById(any());
    }


    @Test
    void shouldNotSaveNewTransactionWhenAccountNotFound() {

        TransactionDTO transactionDTO = new TransactionDTO(2,
                1,
                new BigDecimal("520.25"));

        when(accountRepository.findById(anyInt())).thenReturn(Optional.empty());

        PismoException pismoException = assertThrows(
                PismoException.class,
                () -> transactionService.save(transactionDTO));

        verify(accountRepository, times(1)).findById(anyInt());
        assertEquals(pismoException.getMessage(), BusinessError.CONTA_NAO_ENCONTRADA.getErrorMessage());
    }

    @Test
    void shouldNotSaveNewTransactionWhenOperationNotFound() {

        Account account = new Account();
        account.setId(10);
        account.setDocumentNumber(987654321L);

        TransactionDTO transactionDTO = new TransactionDTO(2,
                1,
                new BigDecimal("520.25"));

        when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        when(operationRepository.findById(any())).thenReturn(Optional.empty());

        PismoException pismoException = assertThrows(
                PismoException.class,
                () -> transactionService.save(transactionDTO));

        verify(accountRepository, times(1)).findById(anyInt());
        assertEquals(pismoException.getMessage(), BusinessError.OPERACAO_NAO_ENCONTRADA.getErrorMessage());
    }
}
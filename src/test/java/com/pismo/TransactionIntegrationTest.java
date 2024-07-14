package com.pismo;


import com.pismo.model.Account;
import com.pismo.model.Operation;
import com.pismo.model.OperationType;
import com.pismo.model.Transaction;
import com.pismo.repository.AccountRepository;
import com.pismo.repository.OperationRepository;
import com.pismo.repository.TransactionRepository;
import jakarta.validation.ConstraintViolationException;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@ActiveProfiles("test")
class TransactionIntegrationTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    Flyway flyway;

    private Account account;
    private Operation operation;


    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();
        account = new Account();
        account.setDocumentNumber(Long.valueOf("12345678900"));
        accountRepository.save(account);

        operation = new Operation();
        operation.setOperationType(OperationType.PAGAMENTO.name());
        operationRepository.save(operation);
    }

    @Test
    void shouldSaveTransactionWithSucess() {

        Optional<Account> account = accountRepository.findById(1);
        Optional<Operation> operationOptional = operationRepository.findById(1);

        Transaction transaction = new Transaction();

        transaction.setAccount(account.get());
        transaction.setOperation(operationOptional.get());
        transaction.setAmount(BigDecimal.ONE);

        Transaction save = transactionRepository.save(transaction);

        assertNotNull(save);
        assertTrue(save.getId() > 0);
    }

    @Test
    void shouldNotSaveTransactionWhithoutAccount() {
        Transaction transaction = new Transaction();
        transaction.setAccount(null);
        ConstraintViolationException constraintViolationException =
                assertThrows(ConstraintViolationException.class, () -> {
                    transactionRepository.save(transaction);
                });

        assertNotNull(constraintViolationException);
    }

    @Test
    void shouldNotSaveTransactionWhithoutOperation() {
        Account account = new Account();
        account.setId(1);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperation(null);

        ConstraintViolationException constraintViolationException =
                assertThrows(ConstraintViolationException.class, () -> {
                    transactionRepository.save(transaction);
                });

        assertNotNull(constraintViolationException);
    }
}

package com.pismo.service;


import com.pismo.model.Account;
import com.pismo.repository.AccountRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.TransactionSystemException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@ActiveProfiles("test")
class AccountIntegrationTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    Flyway flyway;

    private Account account;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();
        account = new Account();
        account.setDocumentNumber(Long.valueOf("12345678900"));
        accountRepository.save(account);
    }

    @Test
    void shouldSaveAccountWithSucess() {

        Account save = accountRepository.save(account);
        assertNotNull(save);
        assertTrue(save.getId() > 0);
    }

    @Test
    void shouldNotSaveAccountWhithoutNumber() {
        account.setDocumentNumber(null);

        TransactionSystemException transactionSystemException =
                assertThrows(TransactionSystemException.class, () -> {
                    accountRepository.save(account);
                });

        assertNotNull(transactionSystemException);
    }
}

package com.pismo.service;

import com.pismo.resource.dto.AccountDTO;
import com.pismo.model.Account;
import com.pismo.resource.exception.BusinessError;
import com.pismo.resource.exception.PismoException;
import com.pismo.service.mapper.AccountMapper;
import com.pismo.service.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountService accountService;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveAccount() throws PismoException {

        AccountDTO accountDTO = new AccountDTO(1234567890L);

        when(accountMapper.toAccount(any())).thenReturn(new Account());

        accountService.save(accountDTO);

        verify(accountRepository, times(1)).save(any());
        verify(accountMapper, times(1)).toAccount(any());

    }

    @Test
    void shouldNotSaveAccountToExistingAccount() {
        AccountDTO accountDTO = new AccountDTO(1234567890L);

        when(accountRepository.save(any())).thenThrow(DataIntegrityViolationException.class);
        
        PismoException pismoException = assertThrows(
                PismoException.class,
                () -> accountService.save(accountDTO));

        assertEquals(pismoException.getMessage(), BusinessError.NAO_FOI_POSSIVEL_SALVAR_A_CONTA.getErrorMessage());
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    void shouldFindAccountForExistingIdAccount() {
        int id = 1;
        Account accountExisting = new Account();
        accountExisting.setId(id);
        accountExisting.setDocumentNumber(1234567890L);

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(accountExisting));

        Optional<Account> account = accountService.getAccount(id);

        verify(accountRepository, times(1)).findById(id);
        assertFalse(account.isEmpty());
        assertEquals(account.get().getId(), id);
    }

    @Test
    void shouldNotFindAccountForDoesntExistingIdAccount() {
        int id = anyInt();
        Account notExistingAccount = new Account();
        notExistingAccount.setId(id);
        notExistingAccount.setDocumentNumber(1234567890L);

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

//        IllegalArgumentException illegalArgumentException = assertThrows(
//                IllegalArgumentException.class,
//                () -> accountService.getAccount(id),
//                "Não encontrado!"
//        );
//        assertTrue(illegalArgumentException.getMessage().contains("Não encontrado!"));

        Optional<Account> account = accountService.getAccount(id);

        verify(accountRepository, times(1)).findById(id);
        assertTrue(account.isEmpty());
    }

}
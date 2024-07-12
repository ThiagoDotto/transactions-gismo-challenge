package com.pismo.resource;

import com.pismo.resource.dto.AccountDTO;
import com.pismo.model.Account;
import com.pismo.resource.exception.PismoException;
import com.pismo.service.AccountService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@OpenAPIDefinition(info = @Info(title = "Pismo API"))
@Tag(name = "Account")
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "New account", description = "Used to creat a new account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta Criada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class))})
    })
    @PostMapping("/accounts")
    public ResponseEntity<String> creatAccount(@RequestBody AccountDTO accountDTO) throws PismoException {
        accountService.save(accountDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Find accounts", description = "Find accounts for Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Account.class))}
            ),
            @ApiResponse(responseCode = "204", description = "Account not found",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Account> findAccount(@PathVariable int accountId) {
        Optional<Account> account = accountService.getAccount(accountId);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}

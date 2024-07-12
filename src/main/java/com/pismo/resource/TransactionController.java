package com.pismo.resource;

import com.pismo.resource.dto.TransactionDTO;
import com.pismo.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Transaction")
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(summary = "Find accounts", description = "Create transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found")})
    @PostMapping
    public ResponseEntity<Object> newtransactions(@RequestBody TransactionDTO transactionDTO) {
        transactionService.save(transactionDTO);
        return ResponseEntity.ok().build();
    }

}

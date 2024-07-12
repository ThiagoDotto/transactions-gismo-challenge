package com.pismo.service.mapper;

import com.pismo.resource.dto.AccountDTO;
import com.pismo.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(AccountDTO accountDTO);
}

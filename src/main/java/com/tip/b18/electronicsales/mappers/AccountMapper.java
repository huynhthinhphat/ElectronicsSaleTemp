package com.tip.b18.electronicsales.mappers;

import com.tip.b18.electronicsales.dto.AccountDTO;
import com.tip.b18.electronicsales.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toDTO(Account account);
}

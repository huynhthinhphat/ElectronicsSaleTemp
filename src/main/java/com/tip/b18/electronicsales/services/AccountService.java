package com.tip.b18.electronicsales.services;

import com.tip.b18.electronicsales.dto.AccountDTO;
import com.tip.b18.electronicsales.entities.Account;

public interface AccountService {
    AccountDTO loginAccount(AccountDTO accountDTO);
    AccountDTO registerAccount(AccountDTO accountDTO);
    AccountDTO convertToDTO(Account account);
}

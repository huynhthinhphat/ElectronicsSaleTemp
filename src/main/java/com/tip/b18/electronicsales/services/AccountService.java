package com.tip.b18.electronicsales.services;

import com.tip.b18.electronicsales.dto.AccountDTO;
import com.tip.b18.electronicsales.dto.AccountLoginDTO;
import com.tip.b18.electronicsales.dto.AccountRegisterDTO;

public interface AccountService {
    AccountDTO loginAccount(AccountLoginDTO accountLoginDTO);
    AccountDTO registerAccount(AccountRegisterDTO accountRegisterDTO);
}

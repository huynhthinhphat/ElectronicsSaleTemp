package com.tip.b18.electronicsales.services.impls;

import com.tip.b18.electronicsales.dto.AccountDTO;
import com.tip.b18.electronicsales.entities.Account;
import com.tip.b18.electronicsales.exceptions.AccountNotFoundException;
import com.tip.b18.electronicsales.repositories.AccountRepository;
import com.tip.b18.electronicsales.services.AccountService;
import com.tip.b18.electronicsales.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordUtil passwordUtil;

    @Override
    public AccountDTO loginAccount(AccountDTO accountReq) {
        //Find an account by userName
        Account account = accountRepository.findByUserName(accountReq.getUserName())
                .orElseThrow(() -> new AccountNotFoundException("Not found account"));

        //Check password
        boolean checkPassword = passwordUtil.matches(accountReq.getPassword(), account.getPassword());
        if(!checkPassword){
            throw new AccountNotFoundException("Not found account");
        }

        //Convert Account entity to AccountDTO
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setUserName(account.getUserName());
        accountDTO.setRole(account.isRole());

        return accountDTO;
    }
}

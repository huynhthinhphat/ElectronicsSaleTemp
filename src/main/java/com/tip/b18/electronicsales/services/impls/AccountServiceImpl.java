package com.tip.b18.electronicsales.services.impls;

import com.tip.b18.electronicsales.dto.AccountDTO;
import com.tip.b18.electronicsales.entities.Account;
import com.tip.b18.electronicsales.exceptions.LoginMessageException;
import com.tip.b18.electronicsales.exceptions.RegisterMessageException;
import com.tip.b18.electronicsales.repositories.AccountRepository;
import com.tip.b18.electronicsales.services.AccountService;
import com.tip.b18.electronicsales.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
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
                .orElseThrow(() -> new LoginMessageException("Tài khoản hoặc mật khẩu không đúng"));

        //Check password
        boolean checkPassword = passwordUtil.matches(accountReq.getPassword(), account.getPassword());
        if(!checkPassword){
            throw new LoginMessageException("Tài khoản hoặc mật khẩu không đúng");
        }

        //Convert Account entity to AccountDTO
        return convertToDTO(account);
    }

    @Override
    public AccountDTO registerAccount(AccountDTO accountReq) {
        //Find an account by userName
        accountRepository.findByUserName(accountReq.getUserName())
                .ifPresent(account -> {throw new RegisterMessageException("Tài khoản tồn tại");});

        //Encryption password
        String encryptPassword = passwordUtil.encryptPassword(accountReq.getPassword());

        //Create a new account
        Account newAccount = new Account();
        newAccount.setFullName(accountReq.getFullName());
        newAccount.setUserName(accountReq.getUserName());
        newAccount.setPassword(encryptPassword);

        //Save and convert Account entity to AccountDTO
        return convertToDTO(accountRepository.save(newAccount));
    }

    //Convert Account entity to AccountDTO
    @Override
    public AccountDTO convertToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setUserName(account.getUserName());
        accountDTO.setRole(account.isRole());
        return accountDTO;
    }
}

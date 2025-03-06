package com.tip.b18.electronicsales.services.impls;

import com.tip.b18.electronicsales.dto.AccountDTO;
import com.tip.b18.electronicsales.dto.AccountLoginDTO;
import com.tip.b18.electronicsales.dto.AccountRegisterDTO;
import com.tip.b18.electronicsales.entities.Account;
import com.tip.b18.electronicsales.exceptions.AlreadyExistsException;
import com.tip.b18.electronicsales.exceptions.CredentialsException;
import com.tip.b18.electronicsales.exceptions.MessageConstant;
import com.tip.b18.electronicsales.mappers.AccountMapper;
import com.tip.b18.electronicsales.repositories.AccountRepository;
import com.tip.b18.electronicsales.services.AccountService;
import com.tip.b18.electronicsales.services.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordService passwordService;
    private final AccountMapper accountMapper;

    @Override
    public AccountDTO loginAccount(AccountLoginDTO accountLoginDTO) {
        //Find an account by userName
        Account account = accountRepository.findByUserName(accountLoginDTO.getUserName())
                .orElseThrow(() -> new CredentialsException(MessageConstant.INVALID_CREDENTIALS_MESSAGE));

        //Check password
        boolean checkPassword = passwordService.matches(accountLoginDTO.getPassword(), account.getPassword());
        if(!checkPassword){
            throw new CredentialsException(MessageConstant.INVALID_CREDENTIALS_MESSAGE);
        }

        //Convert Account entity to AccountDTO
        return accountMapper.toDTO(account);
    }

    @Override
    public AccountDTO registerAccount(AccountRegisterDTO accountRegisterDTO) {
        //Find an account by userName
        accountRepository.findByUserName(accountRegisterDTO.getUserName())
                .ifPresent(account -> {throw new AlreadyExistsException(MessageConstant.EXIST_ACCOUNT_MESSAGE);});

        //Encrypt password
        String password = passwordService.encryptPassword(accountRegisterDTO.getPassword());

        Account account = new Account();
        account.setFullName(accountRegisterDTO.getFullName());
        account.setUserName(accountRegisterDTO.getUserName());
        account.setPassword(password);
        account.setRole(false);

        //Save and convert Account entity to AccountDTO
        return accountMapper.toDTO(accountRepository.save(account));
    }

}

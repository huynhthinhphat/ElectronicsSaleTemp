package com.tip.b18.electronicsales.controllers;

import com.tip.b18.electronicsales.dto.AccountDTO;
import com.tip.b18.electronicsales.dto.AccountLoginDTO;
import com.tip.b18.electronicsales.dto.AccountRegisterDTO;
import com.tip.b18.electronicsales.dto.ResponseDTO;
import com.tip.b18.electronicsales.exceptions.MessageConstant;
import com.tip.b18.electronicsales.services.AccountService;
import com.tip.b18.electronicsales.services.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AccountService accountService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseDTO<AccountDTO> loginAccount(@RequestBody @Valid AccountLoginDTO accountLoginDTO){
        AccountDTO account = accountService.loginAccount(accountLoginDTO);

        String token = jwtService.generateToken(account.getUserName(), account.getId(), account.isRole());

        ResponseDTO<AccountDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setStatus("success");
        responseDTO.setMessage(MessageConstant.LOGIN_ACCOUNT_SUCCESS);
        responseDTO.setToken(token);
        responseDTO.setData(account);
        return responseDTO;
    }

    @PostMapping("/register")
    public ResponseDTO<AccountDTO> registerAccount(@RequestBody @Valid AccountRegisterDTO accountRegisterDTO){
        AccountDTO account = accountService.registerAccount(accountRegisterDTO);

        ResponseDTO<AccountDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setStatus("success");
        responseDTO.setMessage(MessageConstant.REGISTER_ACCOUNT_SUCCESS);
        responseDTO.setData(account);
        return responseDTO;
    }
}

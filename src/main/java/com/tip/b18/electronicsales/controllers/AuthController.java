package com.tip.b18.electronicsales.controllers;

import com.tip.b18.electronicsales.dto.AccountDTO;
import com.tip.b18.electronicsales.dto.ResponseDTO;
import com.tip.b18.electronicsales.exceptions.LoginMessageException;
import com.tip.b18.electronicsales.exceptions.RegisterMessageException;
import com.tip.b18.electronicsales.services.impls.AccountServiceImpl;
import com.tip.b18.electronicsales.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AccountServiceImpl accountServiceImpl;
    private final JwtUtil jwtUtil;

    private ResponseEntity<ResponseDTO> handleValidationErrors(BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(ResponseDTO.fail("Cần có tài khoản và mật khẩu"));
        }
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginAccount(@RequestBody @Valid AccountDTO accountDTO, BindingResult result){
        ResponseEntity<ResponseDTO> validationError = handleValidationErrors(result);
        if (validationError != null) {
            return validationError;
        }
        try{
            AccountDTO account = accountServiceImpl.loginAccount(accountDTO);

            String token = jwtUtil.generateToken(account.getUserName(), account.getId(), account.isRole());
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.success("Đăng nhập thành công", token, account));
        }catch (LoginMessageException e){
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.success(e.getMessage(), null, null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerAccount(@RequestBody @Valid AccountDTO accountDTO, BindingResult result){
        ResponseEntity<ResponseDTO> validationError = handleValidationErrors(result);
        if (validationError != null) {
            return validationError;
        }
        try {
            AccountDTO account = accountServiceImpl.registerAccount(accountDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDTO.success("Đăng ký thành công", null, account));
        }catch (RegisterMessageException e){
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.success(e.getMessage(), null, null));
        }
    }
}

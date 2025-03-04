package com.tip.b18.electronicsales.controllers;

import com.tip.b18.electronicsales.dto.AccountDTO;
import com.tip.b18.electronicsales.dto.ResponseDTO;
import com.tip.b18.electronicsales.exceptions.AccountNotFoundException;
import com.tip.b18.electronicsales.services.impls.AccountServiceImpl;
import com.tip.b18.electronicsales.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AccountServiceImpl accountServiceImpl;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginAccount(@RequestBody @Valid AccountDTO accountDTO){
        try{
            AccountDTO account = accountServiceImpl.loginAccount(accountDTO);

            String token = jwtUtil.generateToken(account.getUserName(), account.getId(), account.isRole());
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.success("Login successfully", token, account));
        }catch (AccountNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDTO.fail("Login failed", "Not found account"));
        }
    }
}

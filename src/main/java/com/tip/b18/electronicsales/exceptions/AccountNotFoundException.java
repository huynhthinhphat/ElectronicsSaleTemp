package com.tip.b18.electronicsales.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String msg){
        super(msg);
    }
}

package com.tip.b18.electronicsales.dto;

import com.tip.b18.electronicsales.exceptions.MessageConstant;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AccountRegisterDTO extends AccountLoginDTO{
    @NotBlank(message = MessageConstant.ACCOUNT_REQUIRED_MESSAGE)
    private String fullName;
}

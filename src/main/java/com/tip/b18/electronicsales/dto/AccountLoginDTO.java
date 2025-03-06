package com.tip.b18.electronicsales.dto;

import com.tip.b18.electronicsales.exceptions.MessageConstant;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountLoginDTO {
    @NotBlank(message = MessageConstant.ACCOUNT_REQUIRED_MESSAGE)
    private String userName;
    @NotBlank(message = MessageConstant.ACCOUNT_REQUIRED_MESSAGE)
    private String password;
}

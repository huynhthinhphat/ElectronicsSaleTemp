package com.tip.b18.electronicsales.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
    private UUID id;
    private String fullName;
    private String userName;
    private String password;
    private boolean role;
}

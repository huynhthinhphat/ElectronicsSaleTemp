package com.tip.b18.electronicsales.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountDTO {
    private UUID id;
    private String fullName;
    private String userName;
    private String email;
    private String address;
    private boolean role;
}

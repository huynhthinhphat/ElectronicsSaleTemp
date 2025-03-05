package com.tip.b18.electronicsales.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private UUID id;
    private String fullName;

    @NotBlank
    private String userName;
    private String email;
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank
    private String password;
    private boolean role;
}

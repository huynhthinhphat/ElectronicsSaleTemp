package com.tip.b18.electronicsales.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ResponseDTO {
    private String status;
    private String message;
    private String errorCode;
    private String token;
    private Object data;

    public static ResponseDTO success(String message, String token, Object data){
        return new ResponseDTO("success", message, null, token, data);
    }

    public static ResponseDTO fail(String message, String errorCode){
        return new ResponseDTO("error", message, errorCode, null, null);
    }
}

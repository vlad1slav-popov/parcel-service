package com.api.parcelservice.domain.login;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordResponse {

    private String username;
    private String message;
    private String responseCode;
}

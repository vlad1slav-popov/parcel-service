package com.api.parcelservice.domain.login;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserResponse {

    private String username;
    private String role;
    private String message;
    private String responseCode;

}

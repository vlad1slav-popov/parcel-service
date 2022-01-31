package com.api.parcelservice.domain.login;


import lombok.Data;

@Data
public class UserLoginRequest {

    private String username;
    private String password;


}

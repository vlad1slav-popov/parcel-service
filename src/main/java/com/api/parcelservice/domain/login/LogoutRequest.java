package com.api.parcelservice.domain.login;

import lombok.Data;

@Data
public class LogoutRequest {

    private String token;
    private String username;
}

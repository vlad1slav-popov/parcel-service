package com.api.parcelservice.controller;



import com.api.parcelservice.domain.login.RegisterUserRequest;
import com.api.parcelservice.domain.login.RegisterUserResponse;
import com.api.parcelservice.domain.login.UserLoginRequest;
import com.api.parcelservice.domain.login.UserLoginResponse;
import com.api.parcelservice.service.AuthenticationService;
import com.api.parcelservice.service.UserAuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserAuthorizationService userAuthorizationService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserAuthorizationService userAuthorizationService) {
        this.authenticationService = authenticationService;
        this.userAuthorizationService = userAuthorizationService;
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest requestDto) {
        return authenticationService.getLoginResponse(requestDto);
    }

//    @PostMapping("/changepassword")
//    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request) {
//        return userAuthorizationService.getChangePasswordResponse(request);
//    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest request) {
        return userAuthorizationService.getRegisterResponse(request);
    }

}

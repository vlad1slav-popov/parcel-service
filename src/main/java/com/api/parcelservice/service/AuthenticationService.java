package com.api.parcelservice.service;



import com.api.parcelservice.domain.login.UserLoginRequest;
import com.api.parcelservice.domain.login.UserLoginResponse;
import com.api.parcelservice.entity.Status;
import com.api.parcelservice.entity.UserLoginEntity;
import com.api.parcelservice.exception.UserInactiveException;
import com.api.parcelservice.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserLoginService userLoginService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager,
                                 JwtTokenProvider jwtTokenProvider,
                                 UserLoginService userLoginService,
                                 BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userLoginService = userLoginService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    public ResponseEntity<UserLoginResponse> getLoginResponse(UserLoginRequest requestDto) {
        try {
            String username = requestDto.getUsername();
            UserLoginEntity user = userLoginService.getUserDataByUsernameAndPassword(requestDto);

            if (!user.getStatus().equals(Status.ACTIVE)) {
                throw new UserInactiveException("USER STATUS IS: " + user.getStatus());
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    requestDto.getPassword()));

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "Bearer_" + token);

            UserLoginResponse userLoginResponse = UserLoginResponse.builder()
                    .username(username)
                    .role(user.getRoles().get(0).getName())
                    .build();

            return ResponseEntity.ok()
                    .headers(httpHeaders)
                    .body(userLoginResponse);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }


}

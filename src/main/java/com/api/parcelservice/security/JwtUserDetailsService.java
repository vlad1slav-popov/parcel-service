package com.api.parcelservice.security;


import com.api.parcelservice.entity.UserLoginEntity;
import com.api.parcelservice.exception.UserNotFoundException;
import com.api.parcelservice.security.jwt.JwtUser;
import com.api.parcelservice.security.jwt.JwtUserFactory;
import com.api.parcelservice.service.UserLoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserLoginService userLoginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginEntity userLoginEntity = Optional.ofNullable(userLoginService.getUserDataByUsername(username))
                .orElseThrow(() -> new UserNotFoundException("USER_NOT_FOUND"));

        JwtUser jwtUser = JwtUserFactory.create(userLoginEntity);

        log.info("IN loadByUserName jwt user: " + jwtUser);
        return jwtUser;
    }
}

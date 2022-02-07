package com.api.parcelservice.security;


import com.api.parcelservice.dto.LoginDTO;
import com.api.parcelservice.security.jwt.JwtUser;
import com.api.parcelservice.security.jwt.JwtUserFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserLoginEntity userLoginEntity = Optional.ofNullable(userLoginRepository.findUserEntityByUsername(username))
//                .orElseThrow(() -> new UserNotFoundException("USER_NOT_FOUND"));
//
//        JwtUser jwtUser = JwtUserFactory.create(userLoginEntity);
//
//        log.info("IN loadByUserName jwt user: " + jwtUser);
//        return jwtUser;
        return null;
    }

    public UserDetails loadUserByUserEntity(LoginDTO entity) {
        JwtUser jwtUser = JwtUserFactory.create(entity);
        return jwtUser;
    }


}

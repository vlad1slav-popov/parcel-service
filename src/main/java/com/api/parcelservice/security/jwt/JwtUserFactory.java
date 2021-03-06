package com.api.parcelservice.security.jwt;


import com.api.parcelservice.dto.LoginDTO;
import com.api.parcelservice.entity.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(LoginDTO userLoginEntity) {


        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        SimpleGrantedAuthority role = new SimpleGrantedAuthority(userLoginEntity.getRoles().get(0));
        roles.add(role);

        return JwtUser.builder()
                .id(userLoginEntity.getId())
                .username(userLoginEntity.getUsername())
                .password(userLoginEntity.getPassword())
                .authorities(roles)
                .enabled(userLoginEntity.getStatus().equals(Status.ACTIVE))
                .build();
    }

//    private static List<GrantedAuthority> getGrantedAuthorityList(List<RoleEntity> userRoleEntities) {
//
//        log.info("get granted authority list: " + userRoleEntities.get(0).getName());
//
//        return userRoleEntities.stream()
//                .map(role ->
//                        new SimpleGrantedAuthority(role.getName())
//                ).collect(Collectors.toList());
//    }

}

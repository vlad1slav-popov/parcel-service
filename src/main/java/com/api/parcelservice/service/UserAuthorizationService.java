package com.api.parcelservice.service;


import com.api.parcelservice.domain.login.ChangePasswordRequest;
import com.api.parcelservice.domain.login.ChangePasswordResponse;
import com.api.parcelservice.domain.login.RegisterUserRequest;
import com.api.parcelservice.domain.login.RegisterUserResponse;
import com.api.parcelservice.entity.RoleEntity;
import com.api.parcelservice.entity.Status;
import com.api.parcelservice.entity.UserLoginEntity;
import com.api.parcelservice.exception.ChangePasswordException;
import com.api.parcelservice.exception.RegisterException;
import com.api.parcelservice.exception.UserNotFoundException;
import com.api.parcelservice.repository.RoleRepository;
import com.api.parcelservice.repository.UserLoginRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserAuthorizationService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserLoginRepository userLoginRepository;

    private final RoleRepository roleRepository;

    public UserAuthorizationService(BCryptPasswordEncoder bCryptPasswordEncoder,
                                    UserLoginRepository userLoginRepository,
                                    RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userLoginRepository = userLoginRepository;
        this.roleRepository = roleRepository;
    }


    public ResponseEntity<RegisterUserResponse> getRegisterResponse(RegisterUserRequest request) {

        if (Objects.isNull(request.getPassword()) ||
                Objects.isNull(request.getUsername())) {
            throw new RegisterException("Username or password are empty");
        }

        if (request.getPassword().trim().isEmpty() ||
                request.getUsername().trim().isEmpty()) {
            throw new RegisterException("password or username is empty");
        }

        if (Objects.nonNull(userLoginRepository.findUserEntityByUsername(request.getUsername()))) {
            throw new RegisterException("Username already exists");
        }

        String encodedPass = bCryptPasswordEncoder.encode(request.getPassword());
        RoleEntity user = roleRepository.findRoleByName("ROLE_USER");
        List<RoleEntity> roleEntityList = new ArrayList<>();
        roleEntityList.add(user);

        UserLoginEntity userLoginEntity = UserLoginEntity.builder()
                .username(request.getUsername())
                .password(encodedPass)
                .roles(roleEntityList)
                .status(Status.ACTIVE)
                .build();


        userLoginRepository.save(userLoginEntity);

        RegisterUserResponse response = RegisterUserResponse.builder()
                .username(request.getUsername())
                .responseCode("0")
                .message("OK")
                .role(user.getName())
                .build();

        return ResponseEntity.ok(response);

    }


}

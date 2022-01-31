package com.api.parcelservice.repository;


import com.api.parcelservice.entity.UserLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Long> {


    UserLoginEntity findUserEntityByUsername(String username);

    UserLoginEntity findUserEntityByUsernameAndPassword(String username, String password);


}


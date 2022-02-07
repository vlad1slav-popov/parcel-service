package com.api.parcelservice.dto;

import com.api.parcelservice.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {


    private Long id;
    private String username;
    private String password;
    private Status status;
    private List<String> roles;
//    private List<RoleEntity> roles;

}

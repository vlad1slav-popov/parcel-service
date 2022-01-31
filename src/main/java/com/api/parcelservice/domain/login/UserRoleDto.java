package com.api.parcelservice.domain.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleDto {

    private String userName;
    private String roleName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

}

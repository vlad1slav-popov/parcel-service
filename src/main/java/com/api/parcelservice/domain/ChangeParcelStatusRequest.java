package com.api.parcelservice.domain;

import com.api.parcelservice.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChangeParcelStatusRequest {


    @NotNull
    private Long id;

    @NotBlank
    private Status status;
}

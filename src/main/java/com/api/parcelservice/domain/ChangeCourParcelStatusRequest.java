package com.api.parcelservice.domain;

import com.api.parcelservice.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChangeCourParcelStatusRequest {

    @NotNull
    private Long id;

    @NotNull
    @JsonProperty("courier_id")
    private Long courierId;

    @NotBlank
    private Status status;

}

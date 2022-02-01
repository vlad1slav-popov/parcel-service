package com.api.parcelservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CancelRequest {

    @NotNull
    private Long id;
    @NotNull
    @JsonProperty("user_id")
    private Long userId;
}

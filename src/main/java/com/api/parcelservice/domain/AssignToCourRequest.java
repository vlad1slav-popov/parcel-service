package com.api.parcelservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AssignToCourRequest {
    @NotNull
    private Long id;
    @NotNull
    @JsonProperty("courier_id")
    private Long courierId;
}

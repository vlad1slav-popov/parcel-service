package com.api.parcelservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdDestinationRequest {


    @NotNull
    private Long id;
    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("coordinates_from")
    private String coordinatesFrom;
    @JsonProperty("coordinates_to")
    private String coordinatesTo;
}

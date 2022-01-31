package com.api.parcelservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdDestinationRequest {

    @NotNull
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;
    @JsonProperty("coordinates_from")
    private String coordinatesFrom;
    @JsonProperty("coordinates_to")
    private String coordinatesTo;
}

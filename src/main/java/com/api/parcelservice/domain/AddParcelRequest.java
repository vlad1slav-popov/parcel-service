package com.api.parcelservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AddParcelRequest {

    @NotNull
    private BigDecimal amount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @NotNull
    @JsonProperty("user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;

    @JsonProperty("courier_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long courierId;

    @NotNull
    @JsonProperty("coordinates_from")
    private String coordinatesFrom;

    @NotNull
    @JsonProperty("coordinates_to")
    private String coordinatesTo;

}

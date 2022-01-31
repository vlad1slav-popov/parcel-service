package com.api.parcelservice.domain;

import com.api.parcelservice.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChangeParcelStatusRequest {

    @NotNull
    @JsonProperty("order_id")
    private String orderId;
//    @JsonProperty("user_id")
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private Long userId;
//
//    @JsonProperty("courier_id")
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private Long courierId;

    @NotBlank
    private Status status;
}

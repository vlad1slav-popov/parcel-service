package com.api.parcelservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class ParcelEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("order_id")
    private String orderId;
    private BigDecimal amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonProperty("user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;
    @JsonProperty("courier_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long courierId;
    @JsonProperty("coordinates_from")
    private String coordinatesFrom;
    @JsonProperty("coordinates_to")
    private String coordinatesTo;
    private Status status;


}

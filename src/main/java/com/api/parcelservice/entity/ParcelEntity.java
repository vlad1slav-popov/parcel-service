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
@Table(name = "parcels")
public class ParcelEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonProperty("user_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "user_id")
    private Long userId;
    @JsonProperty("courier_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "courier_id")
    private Long courierId;
    @JsonProperty("coordinates_from")
    @Column(name = "coordinates_from")
    private String coordinatesFrom;
    @JsonProperty("coordinates_to")
    @Column(name = "coordinates_to")
    private String coordinatesTo;
    @Enumerated(EnumType.STRING)
    private Status status;


}

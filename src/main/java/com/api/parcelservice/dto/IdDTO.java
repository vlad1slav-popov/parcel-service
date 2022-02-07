package com.api.parcelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdDTO {

    private Long userId;
    private Long courId;
    private Long parcelId;
}

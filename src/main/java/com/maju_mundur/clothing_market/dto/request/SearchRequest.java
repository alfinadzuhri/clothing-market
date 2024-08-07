package com.maju_mundur.clothing_market.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchRequest {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}

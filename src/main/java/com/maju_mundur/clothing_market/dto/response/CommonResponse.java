package com.maju_mundur.clothing_market.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse<T> {
    private Integer statusCode;
    private String message;
    private T data; // nanti datanya bisa dinamis, array atau object
    private PagingResponse paging;
}

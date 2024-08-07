package com.maju_mundur.clothing_market.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    private String username;
    private String password;
}

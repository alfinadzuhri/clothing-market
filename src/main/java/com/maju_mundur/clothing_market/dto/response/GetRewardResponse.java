package com.maju_mundur.clothing_market.dto.response;

import com.maju_mundur.clothing_market.entity.Reward;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetRewardResponse {

    private Reward reward;
    private Integer rewardValue;

}

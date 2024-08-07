package com.maju_mundur.clothing_market.entity;

import com.maju_mundur.clothing_market.constant.ConstantTable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.ORDERED_REWARD)
public class OrderedReward {
}

package com.maju_mundur.clothing_market.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.maju_mundur.clothing_market.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.REWARD_RULES)
public class RewardRules {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "reward_id", nullable = false)
    @JsonBackReference
    private Reward reward;

    @Column(name = "threshold", nullable = false)
    private Long threshold;

    @Column(name = "reward_value", nullable = false)
    private Integer rewardValue;

    @Column(name = "created_at", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}

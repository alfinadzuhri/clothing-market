package com.maju_mundur.clothing_market.entity;

import com.maju_mundur.clothing_market.constant.ConstantTable;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = ConstantTable.TRANSACTION_DETAIL)
public class TransactionDetail {
}

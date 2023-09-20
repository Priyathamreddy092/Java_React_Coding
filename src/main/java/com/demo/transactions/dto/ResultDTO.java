package com.demo.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    Long totalTransactionAmount;
    Long totalRewardsEarned;
}

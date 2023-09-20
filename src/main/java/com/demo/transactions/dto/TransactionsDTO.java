package com.demo.transactions.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class TransactionsDTO {
    private Integer transactionId;
    private Integer customerId;
    private double amount;
    private Date transactionDate;
}
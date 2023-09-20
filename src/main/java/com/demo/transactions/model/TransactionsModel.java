package com.demo.transactions.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity(name = "TRANSACTION_MODEL")

@Getter
@Setter
public class TransactionsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="TRANSACTION_ID")
    private Long transactionId;
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Column(name = "AMOUNT")
    private double amount;
    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

}


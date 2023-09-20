package com.demo.transactions.controller;

import com.demo.transactions.dto.ResultDTO;
import com.demo.transactions.dto.TransactionsDTO;
import com.demo.transactions.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("customer")
public class TransactionController {
    private TransactionService transactionService;

    @GetMapping(path = "/transactions")
    public ResponseEntity<List<TransactionsDTO>> getCustomerTransactions(@RequestParam int customerId){
        ResponseEntity<List<TransactionsDTO>> responseEntity = null;
        responseEntity = new ResponseEntity<>(transactionService.getTransactionsByCustomerId(customerId),HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(path = "/rewards/{customerId}")
    public ResponseEntity<ResultDTO> getCustomerRewards(@PathVariable int customerId){
        ResponseEntity<ResultDTO> responseEntity = null;
        responseEntity = new ResponseEntity<>(transactionService.getRewards(customerId),HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(path = "/transactions/{transactionId}")
    public ResponseEntity<TransactionsDTO> getTransactionById(@PathVariable int transactionId){
        ResponseEntity<TransactionsDTO> responseEntity = null;
        responseEntity = new ResponseEntity<>(transactionService.getTransactionsByTransactionId(transactionId),HttpStatus.OK);
        return responseEntity;
    }

}





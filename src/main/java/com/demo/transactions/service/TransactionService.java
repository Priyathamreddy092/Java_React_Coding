package com.demo.transactions.service;

import com.demo.transactions.dto.ResultDTO;
import com.demo.transactions.dto.TransactionsDTO;
import com.demo.transactions.model.TransactionsModel;
import com.demo.transactions.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;
    public double calculateRewardPoints(double amount) {
        double rewardPoints = 0;
        if (amount > 100) {
            rewardPoints += 2 * (double) (amount - 100);
        }
        if (amount > 50 && amount <= 100) {
            rewardPoints += (double) (amount - 50);
        }
        return rewardPoints;
    }

    public ResultDTO getRewards(int customerId){
        List<TransactionsModel> transactionModels = transactionRepository.findByCustomerId(customerId);
        double totalTransactions = transactionModels.stream().filter(tm -> {
            Instant transactionDate = tm.getTransactionDate().toInstant();
            Instant threeMonthsAgo = LocalDate.now().minusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant();
            return transactionDate.isAfter(threeMonthsAgo);
        }).mapToDouble(TransactionsModel::getAmount).sum();
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setTotalTransactionAmount((long) (totalTransactions));
        calculateRewardPoints(totalTransactions);
        resultDTO.setTotalRewardsEarned((long) calculateRewardPoints(totalTransactions));
        return resultDTO;
    }

    public TransactionsDTO getTransactionsByTransactionId(int transactionId){
        return modelMapper.map(transactionRepository.findById(transactionId).get(), TransactionsDTO.class);
    }

    public List<TransactionsDTO> getTransactionsByCustomerId(int customerId){
        List<TransactionsModel> transactionModels = transactionRepository.findByCustomerId(customerId);
        return transactionModels.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TransactionsDTO convertToDTO(TransactionsModel transactionModel) {
        return modelMapper.map(transactionModel, TransactionsDTO.class);
    }
}


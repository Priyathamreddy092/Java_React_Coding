package com.demo.transactions.repository;

import com.demo.transactions.model.TransactionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "transaction", path = "transaction")
public interface TransactionRepository extends JpaRepository<TransactionsModel, Integer> {
    List<TransactionsModel> findByCustomerId(int customerId);

}

package com.financetracker.finance_tracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.financetracker.finance_tracker.model.Transaction; 

import com.financetracker.finance_tracker.repository.TransactionRepository;

// handles all the API operations related to transactions 

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    // Declaring dependency here 
    // Injecting TransactionRepository to interact with the database 
    // Dependency Injection via constructor

    private final TransactionRepository transactionRepository;
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        // The repository's save() method performs the SQL INSERT via Hibernate
        return transactionRepository.save(transaction); 
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        // The repository's findAll() method performs the SQL SELECT via Hibernate
        return transactionRepository.findAll(); 
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Transaction not found with id " + id));
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        // 1. Get the existing transaction (ensure it exists)
        Transaction existingTransaction = transactionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transaction not found for id: " + id));
            
        // 2. Update the fields
        existingTransaction.setAmount(transactionDetails.getAmount());
        existingTransaction.setDate(transactionDetails.getDate());
        existingTransaction.setDescription(transactionDetails.getDescription());
        existingTransaction.setType(transactionDetails.getType()); // Make sure to handle the type change

        // 3. Save the updated transaction (which performs an SQL UPDATE)
        return transactionRepository.save(existingTransaction);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
            transactionRepository.findById(id) 
            .orElseThrow(() -> new RuntimeException("Transaction not found with id " + id));
            transactionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
    }


}

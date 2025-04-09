package com.simply.simple_life.Controller;


import com.simply.simple_life.DTO.Transactions.TransactionRequest;
import com.simply.simple_life.Entity.Transactions;
import com.simply.simple_life.Exceptions.UserNotFoundException;
import com.simply.simple_life.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;


    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping
    public ResponseEntity<List<Transactions>> getTransactions(@RequestParam int userId) throws UserNotFoundException {
        List<Transactions> transactions = transactionService.getTransactionsByUserId(userId);
        if(transactions.isEmpty()) {
            return ResponseEntity.notFound().build();

        } else {
            return ResponseEntity.ok(transactions);
        }
    }

    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@RequestBody TransactionRequest transaction) throws UserNotFoundException {
        System.out.println(transaction.toString());
        Transactions transactionAdded = transactionService.addTransactionToUser(transaction);
        return transactionAdded != null ? ResponseEntity.status(HttpStatus.CREATED).body(transactionAdded) : ResponseEntity.notFound().build();
    }
//
    @PutMapping
    public ResponseEntity<Transactions> updateTransaction(@RequestBody TransactionRequest transaction) throws UserNotFoundException {
        Transactions transactionUpdated = transactionService.updateTransaction(transaction);
        return transactionUpdated != null ? ResponseEntity.ok(transactionUpdated) : ResponseEntity.notFound().build();
    }


    @DeleteMapping
    public ResponseEntity<String> deleteTransaction(@RequestParam int id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }



}

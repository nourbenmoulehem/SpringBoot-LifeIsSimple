package com.simply.simple_life.Service;

import com.simply.simple_life.DTO.Transactions.TransactionRequest;
import com.simply.simple_life.Entity.Categories;
import com.simply.simple_life.Entity.Transactions;
import com.simply.simple_life.Entity.Users;
import com.simply.simple_life.Exceptions.CategoryNotFoundException;
import com.simply.simple_life.Exceptions.TransactionNotFoundException;
import com.simply.simple_life.Exceptions.UserNotFoundException;
import com.simply.simple_life.Repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component // marks java class as component; should be managed by spring container and manage its lifecycle
public class TransactionService {

    private final TransactionsRepository transactionsRepository;
    private final UsersService usersService;
    private final CategoryService categoryService;

    @Autowired
    public TransactionService(TransactionsRepository transactionsRepository, UsersService usersService, CategoryService categoryService) {
        this.transactionsRepository = transactionsRepository;
        this.usersService = usersService;
        this.categoryService = categoryService;
    }



    public List<Transactions> getTransactionsByUserId(int userId) throws UserNotFoundException {
        Users user = usersService.getUserById(userId)
                        .orElseThrow(() -> new UserNotFoundException(userId));
        System.out.println("----------------------");
        System.out.println(user.toString());
        List<Transactions> transactions = transactionsRepository.findByUser(user);
        //System.out.println(transactions.getFirst().toString());
        if (!transactions.isEmpty()) {
            System.out.println(transactions.get(0).toString());
        }
        return transactionsRepository.findByUser(user);
    }

//    public List<Transaction> getTransactionsByCategoryId(int categoryId, int userId) {
//        return transactionsRepository.findTransactionsByCategoryIdAndUserId(categoryId, userId);
//    }
//
//    public List<Transaction> getTransactionsByAmountBiggerThen(int amount, int userId) {
//        Users user = usersService.getUserById(userId);
//        return transactionsRepository.findAllByUserId(user).stream()
//                .filter(transaction -> transaction.getAmount() > amount)
//                .collect(Collectors.toList());
//    }
//
    public Transactions addTransactionToUser(TransactionRequest transactionRequest) throws UserNotFoundException {
        System.out.println("*******************************");
        Users user = usersService.getUserById(transactionRequest.getUserId())
                        .orElseThrow(() -> new UserNotFoundException(transactionRequest.getUserId()));
        System.out.println("----------------------");
        System.out.println(transactionRequest.toString());
        System.out.println(user == null ? "user is null" : user.toString());

        Categories category = categoryService.getCategoryById(transactionRequest.getCategoryId())
                        .orElseThrow(() -> new CategoryNotFoundException(transactionRequest.getCategoryId()));
        System.out.println("-----------------------");
        System.out.println(category.toString());

        Transactions transaction = new Transactions();
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setName(transactionRequest.getName());
        transaction.setDate(transactionRequest.getDate());
        return transactionsRepository.save(transaction);
    }
//
//    public List<Transaction> getTransactionsByName(int userId, String name) {
//        Users user = usersService.getUserById(userId);
//        return transactionsRepository.findAllByUserId(user).stream()
//                .filter(transaction -> transaction.getName().equalsIgnoreCase(name))
//                .collect(Collectors.toList());
//
//    }
//
//    // total expense
//    public double getTotalExpenses(int userId) {
//        Users user = usersService.getUserById(userId);
//        return transactionsRepository.findAllByUserId(user)
//                .stream()
//                .filter(transaction -> transaction.getCategoryId().getType().equals("EXPENSE"))
//                .mapToDouble(Transaction::getAmount)
//                .sum();
//
//    }
//
//
//    public double getTotalIncome(int userId) {
//        Users user = usersService.getUserById(userId);
//        return transactionsRepository.findAllByUserId(user)
//                .stream()
//                .filter(transaction -> transaction.getCategoryId().getType().equals("INCOME"))
//                .mapToDouble(Transaction::getAmount)
//                .sum();
//
//    }
//
//
    public Transactions updateTransaction(TransactionRequest request) throws UserNotFoundException {
        Transactions transaction = transactionsRepository.findById(request.getId())
                .orElseThrow(() -> new TransactionNotFoundException(request.getId())); // New exception

        Users user = usersService.getUserById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        Categories category = categoryService.getCategoryById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(request.getCategoryId()));

        // Update fields
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setName(request.getName());
        transaction.setDate(request.getDate());

        return transactionsRepository.save(transaction);
    }

    public void deleteTransaction(Transactions transaction) {
        transactionsRepository.delete(transaction);
    }




}

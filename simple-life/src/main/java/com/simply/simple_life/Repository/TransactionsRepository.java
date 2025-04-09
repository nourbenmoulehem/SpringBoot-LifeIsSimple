package com.simply.simple_life.Repository;

import com.simply.simple_life.Entity.Transactions;
import com.simply.simple_life.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {



    //Optional<Transaction> findByName(String name); // optional handle the possibility of null by throwing NullPointerException


    //List<Transaction> findAllByUserId(Users user);

    //@Query("SELECT t FROM Transaction t WHERE t.categoryId = :categoryId AND t.userId = :userId")
    //List<Transaction> findTransactionsByCategoryIdAndUserId(@Param("categoryId") int categoryId, @Param("userId") int userId);

    List<Transactions> findByUser(Users user);
}

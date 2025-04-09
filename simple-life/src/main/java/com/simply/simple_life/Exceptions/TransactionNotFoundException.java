package com.simply.simple_life.Exceptions;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(int id){
        super("Transaction not found with id " + id);
    }
}

package com.example.rest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by bquinn on 7/21/2016.
 */
public class TransactionProcessor {

    private String accountNumber;
    private BigDecimal amount;
    private List<Transaction> transactions;
    private BigDecimal currentBalance;

    TransactionProcessor() {

    }

    TransactionProcessor(String accountNumber, TransactionRequest transactionRequest) {
        this.accountNumber = accountNumber;
        this.amount = transactionRequest.getAmount();
        TransactionReader transactionReader = new TransactionReader(accountNumber);
        this.transactions = transactionReader.readTransactions();
        this.currentBalance = transactionReader.getCurrentBalance();

    }

    public Transaction processTransaction() {
        Transaction transaction = new Transaction(this.amount);
        try {
            BigDecimal newBalance = this.currentBalance.add(this.amount);
            if (newBalance.compareTo(BigDecimal.ZERO) == 1) {
                transaction.setTransactionNumber(getNextTransactionNumber());
                transaction.setBalance(newBalance);
                TransactionWriter transactionWriter = new TransactionWriter(this.accountNumber, transaction);
            } else {
                transaction.setTransactionNumber(-1);
                transaction.setBalance(this.currentBalance);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.toString(), e);
        }
        return transaction;
    }

    private int getNextTransactionNumber() {
        return this.transactions.size() + 1;
    }
}

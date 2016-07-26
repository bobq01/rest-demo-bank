package com.example.rest;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bquinn on 7/18/2016.
 */
public class Transaction {
    private int transactionNumber;
    @JsonFormat(pattern=Config.TRANSACTION_DATE_FORMAT)
    private Date transactionDate;

    private BigDecimal amount;
    private BigDecimal balance;

    // Constructors

    public Transaction(int transactionNumber, Date transactionDate, BigDecimal amount, BigDecimal balance) {
        this.transactionNumber = transactionNumber;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.balance = balance;
    }

    public Transaction(BigDecimal amount) {
        this.amount = amount;
        this.transactionDate = new Date();
    }

    // Methods

    // Getters and Setters

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String ToString() {

        DateFormat dateFormatter = new SimpleDateFormat((Config.TRANSACTION_DATE_FORMAT));
        String transactionDate = dateFormatter.format(this.transactionDate);

        StringBuilder stringBuilder = new StringBuilder(1000);
        stringBuilder.append(this.transactionNumber);
        stringBuilder.append("\t");
        stringBuilder.append(transactionDate);
        stringBuilder.append("\t");
        stringBuilder.append(this.amount);
        stringBuilder.append("\t");
        stringBuilder.append(this.balance);
        return stringBuilder.toString();
    }
}

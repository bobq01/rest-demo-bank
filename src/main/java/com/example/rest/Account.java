package com.example.rest;
import java.math.BigDecimal;
import java.util.List;

public class Account {

    private String name;
    private String email;
    private String accountNumber;
    private BigDecimal balance;

    public Account() {

    }

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
     }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String ToString() {

        StringBuilder stringBuilder = new StringBuilder(1000);
        stringBuilder.append(this.accountNumber);
        stringBuilder.append("\t");
        stringBuilder.append(this.name);
        stringBuilder.append("\t");
        stringBuilder.append(this.email);
        return stringBuilder.toString();
    }
}
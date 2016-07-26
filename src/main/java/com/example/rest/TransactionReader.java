package com.example.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionReader {

    String accountNumber;
    List<Transaction> transactions;
    BigDecimal currentBalance;

    TransactionReader() {

    }

    TransactionReader(String accountNumber) {
        this.accountNumber = accountNumber;
        transactions = readTransactions();
        currentBalance = getAccountBalance();
    }

    public List<Transaction> readTransactions() {

        Config config = new Config();
        String accountTransactionsFile = config.getTransactionsFilePath(this.accountNumber);

        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            String[] transactionElements;
            String line;
            File file = new File(accountTransactionsFile);
            if (file.exists()) {
                FileReader fileReader = new FileReader(accountTransactionsFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((line = bufferedReader.readLine()) != null) {
                    transactionElements = line.split("\t");
                    DateFormat dateFormatter = new SimpleDateFormat((config.TRANSACTION_DATE_FORMAT));
                    Date transactionDate = dateFormatter.parse(transactionElements[1]);
                    BigDecimal amount = new BigDecimal(transactionElements[2]);
                    BigDecimal balance = new BigDecimal(transactionElements[3]);
                    Transaction transaction = new Transaction(Integer.parseInt(transactionElements[0]), transactionDate, amount, balance);
                    transactions.add(transaction);
                }
                fileReader.close();
                bufferedReader.close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new RuntimeException("Failed to load transactions from storage: " + e.toString(), e);
        }
        return transactions;
    }

    private BigDecimal getAccountBalance() {
        if (this.transactions.size() > 0) {
            Transaction transaction = this.transactions.get(this.transactions.size() - 1);
            return transaction.getBalance();
        }
        return BigDecimal.ZERO;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }
}
package com.example.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @RequestMapping(method = RequestMethod.GET, value = "/accounts")
    public List<Account> accountsList() {
        return new AccountReader().readAccounts();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accounts")
    public Account accountCreate(@RequestBody Account account) {

        AccountWriter accountWriter = new AccountWriter(account, true);
        String accountNumber = accountWriter.getAccountNumber();
        if (accountNumber != null) {
            Account newAccount = account;
            newAccount.setAccountNumber(accountNumber);
            TransactionReader transactionReader = new TransactionReader(accountNumber);
            newAccount.setBalance(transactionReader.getCurrentBalance());
            return newAccount;
        }
        return account;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/accounts/{accountNumber}")
    public Account accountUpdate(@RequestBody Account account, @PathVariable String accountNumber) {

        AccountWriter accountWriter = new AccountWriter(account, false);
        Account updatedAccount = new AccountReader(accountNumber).readAccountDetails();
        TransactionReader transactionReader = new TransactionReader(accountNumber);
        updatedAccount.setBalance(transactionReader.getCurrentBalance());
        return updatedAccount;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountNumber}")
    public Account accountSummary(@PathVariable String accountNumber) {

        Account account = new AccountReader(accountNumber).readAccountDetails();
        TransactionReader transactionReader = new TransactionReader(accountNumber);
        account.setBalance(transactionReader.getCurrentBalance());
        return account;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/{accountNumber}/transactions")
    public Transaction transactionRequest(@RequestBody TransactionRequest transactionRequest, @PathVariable String accountNumber) {

        TransactionProcessor transactionProcessor = new TransactionProcessor(accountNumber, transactionRequest);
        return transactionProcessor.processTransaction();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{accountNumber}/transactions")
    public List<Transaction> transactionsList(@PathVariable String accountNumber) {
        return new TransactionReader(accountNumber).readTransactions();
    }
}

package com.example.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class AccountReader {

    private String accountNumber;

    public AccountReader() {

    }

    public AccountReader(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Account readAccountDetails() {

        String accountDetailsFile = new Config().getAccountDetailsFilePath(this.accountNumber);

        Account account = new Account();

        try {
            String line;
            String fileText = "";
            FileReader fileReader = new FileReader(accountDetailsFile);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                fileText = fileText + line;
            }
            fileReader.close();
            bufferedReader.close();

            String[] fileData = fileText.split("\t");
            account.setAccountNumber(fileData[0]);
            account.setName(fileData[1]);
            account.setEmail(fileData[2]);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read account info from storage: " + e.toString(), e);
        }
        return account;
    }

    public List<Account> readAccounts() {
        List<Account> accountList = new ArrayList<>();
        try {
            File file = new File(Config.STORAGE_PATH);
            String[] directories = file.list(new FilenameFilter() {
                public boolean accept(File current, String name) {
                    return new File(current, name).isDirectory();
                }
            });

            for (String s : directories) {
                AccountReader reader = new AccountReader(s);
                Account account = reader.readAccountDetails();
                TransactionReader transactionReader = new TransactionReader(s);
                account.setBalance(transactionReader.getCurrentBalance());
                accountList.add(account);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to read account info from storage: " + e.toString(), e);
        }
        return accountList;
    }
}

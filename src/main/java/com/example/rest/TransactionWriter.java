package com.example.rest;


import java.io.File;
import java.io.FileWriter;

public class TransactionWriter {

    TransactionWriter() {

    }

    TransactionWriter(String accountNumber, Transaction transaction) {
        writeTransaction(accountNumber, transaction);
    }

    private void writeTransaction(String accountNumber, Transaction transaction) {

        String newline = System.getProperty("line.separator");
        Config config = new Config();

        try {
            File accountFolder = new File(config.getAccountStorageFolder(accountNumber));
            if (!accountFolder.exists()) {
                accountFolder.mkdir();
            }
            FileWriter fileWriter = new FileWriter(config.getTransactionsFilePath(accountNumber), true);
            fileWriter.write(transaction.ToString());
            fileWriter.write(newline);
            fileWriter.close();

        } catch (Exception e) {

            throw new RuntimeException(e.toString(), e);
        }
    }
}

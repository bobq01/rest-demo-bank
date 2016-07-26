package com.example.rest;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * Created by bquinn on 7/19/2016.
 */
public class AccountWriter {

    String accountNumber;

    AccountWriter() {

    }

    AccountWriter(Account account, boolean IsNewAccount){
        writeAccountInfo(account, IsNewAccount);
    }

    public void writeAccountInfo(Account account, boolean isNewAccount) {
        String newline = System.getProperty("line.separator");
        Config config = new Config();

        if (isNewAccount) {
            account.setAccountNumber(getNextAccountNumber());
        }

        this.accountNumber = account.getAccountNumber();

        try {
            File accountFolder = new File(config.getAccountStorageFolder(account.getAccountNumber()));
            if (!accountFolder.exists()) {
                accountFolder.mkdir();
            }
            FileWriter fileWriter = new FileWriter(config.getAccountDetailsFilePath(account.getAccountNumber()), false);
            fileWriter.write(account.ToString());
            fileWriter.write(newline);
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException("Save of account info failed: " + e.toString(), e);
        }
    }

    public String getNextAccountNumber() {
        File file = new File(Config.STORAGE_PATH);
        String[] directories = file.list(new FilenameFilter() {
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });

        if (directories.length == 0) {
            return "1";
        }

        Arrays.sort(directories);
        return Integer.toString(directories.length + 1);
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

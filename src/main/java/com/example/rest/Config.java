package com.example.rest;

/**
 * Created by bquinn on 7/19/2016.
 */
public class Config {

    public static final String STORAGE_PATH = "C:\\Users\\BQuinn\\Documents\\DemoWebService";
    public static final String ACCOUNT_DETAILS_FILE = "AccountDetail.txt";
    public static final String ACCOUNT_TRANSACTIONS_FILE = "Transactions.txt";
    public static final String TRANSACTION_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    Config () {

    }

    public String getAccountDetailsFilePath (String accountNumber) {

        StringBuilder stringBuilder = new StringBuilder(1000);
        stringBuilder.append(this.STORAGE_PATH);
        stringBuilder.append("\\");
        stringBuilder.append(accountNumber);
        stringBuilder.append("\\");
        stringBuilder.append(this.ACCOUNT_DETAILS_FILE);
        return stringBuilder.toString();
    }

    public String getAccountStorageFolder (String accountNumber) {

        StringBuilder stringBuilder = new StringBuilder(1000);
        stringBuilder.append(this.STORAGE_PATH);
        stringBuilder.append("\\");
        stringBuilder.append(accountNumber);
        stringBuilder.append("\\");
        return stringBuilder.toString();
    }

    public String getTransactionsFilePath (String accountNumber) {

        StringBuilder stringBuilder = new StringBuilder(1000);
        stringBuilder.append(this.STORAGE_PATH);
        stringBuilder.append("\\");
        stringBuilder.append(accountNumber);
        stringBuilder.append("\\");
        stringBuilder.append(this.ACCOUNT_TRANSACTIONS_FILE);
        return stringBuilder.toString();
    }

    public static String getStoragePath() {
        return STORAGE_PATH;
    }

    public static String getAccountDetailsFile() {
        return ACCOUNT_DETAILS_FILE;
    }

    public static String getAccountTransactionsFile() {
        return ACCOUNT_TRANSACTIONS_FILE;
    }

    public static String getTransactionDateFormat() {
        return TRANSACTION_DATE_FORMAT;
    }
}

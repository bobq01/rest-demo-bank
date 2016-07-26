package com.example.rest;

import java.math.BigDecimal;

/**
 * Created by bquinn on 7/21/2016.
 */
public class TransactionRequest {
    BigDecimal amount;

    TransactionRequest() {

    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

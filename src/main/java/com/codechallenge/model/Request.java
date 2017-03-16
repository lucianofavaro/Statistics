package com.codechallenge.model;

import com.google.common.base.MoreObjects;

import javax.validation.constraints.NotNull;

public class Request {

    @NotNull
    private Double amount;

    @NotNull
    private Long timestamp;

    public Request() {
    }

    public Request(Double amount, Long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("amount", amount)
                .add("timestamp", timestamp)
                .toString();
    }
}

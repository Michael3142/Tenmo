package com.techelevator.tenmo.model;

public class Transfer {
    private int transferId;

    private int fromAccount;

    private int toAccount;

    private double amountSending;

    private String status;

    public Transfer(int transferId, int fromAccount, int toAccount, double amountSending, String status) {
        this.transferId = transferId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amountSending = amountSending;
        this.status = status;
    }

    public Transfer() {
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public void setToAccount(int toAccount) {
        this.toAccount = toAccount;
    }

    public double getAmountSending() {
        return amountSending;
    }

    public void setAmountSending(double amountSending) {
        this.amountSending = amountSending;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

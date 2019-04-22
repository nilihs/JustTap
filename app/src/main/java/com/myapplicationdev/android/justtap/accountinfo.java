package com.myapplicationdev.android.justtap;

public class accountinfo {

    String accountId;
    String accountName;
    int accountScore;

    public accountinfo(String accountId, String accountName, int accountScore) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountScore = accountScore;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getAccountScore() {
        return accountScore;
    }

    public void setAccountScore(int accountScore) {
        this.accountScore = accountScore;
    }
}

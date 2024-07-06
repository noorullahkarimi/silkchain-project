package com.silkchain.Silkchain.service;

public class LoginRequest {
    private String walletAddress;
    private String password;

    // getters and setters

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

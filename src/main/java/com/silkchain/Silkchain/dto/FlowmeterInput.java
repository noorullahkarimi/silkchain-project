package com.silkchain.Silkchain.dto;
import javax.persistence.Embeddable;

@Embeddable
public class FlowmeterInput {
    private float input;
    private String walletAddress;

    // Constructors
    public FlowmeterInput() {}

    public FlowmeterInput(float input, String walletAddress) {
        this.input = input;
        this.walletAddress = walletAddress;
    }

    // Getters and Setters
    public float getInput() {
        return input;
    }

    public void setInput(float input) {
        this.input = input;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
}
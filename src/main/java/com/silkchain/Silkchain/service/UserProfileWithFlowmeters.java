package com.silkchain.Silkchain.service;

import com.silkchain.Silkchain.dto.Flowmeter;
import com.silkchain.Silkchain.dto.User;

import java.util.List;

public class UserProfileWithFlowmeters {
    private User user;
    private List<String> flowmeters;

    public UserProfileWithFlowmeters(User user, List<String> flowmeters) {
        this.user = user;
        this.flowmeters = flowmeters;
    }

    // Getters and Setters


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getFlowmeters() {
        return flowmeters;
    }

    public void setFlowmeters(List<String> flowmeters) {
        this.flowmeters = flowmeters;
    }
}
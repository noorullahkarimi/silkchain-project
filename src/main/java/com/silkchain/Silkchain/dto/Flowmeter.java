package com.silkchain.Silkchain.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "flowmeter")
public class Flowmeter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float input;
    private float output;
    private String batchId;
    private String devicecode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getInput() {
        return input;
    }

    public void setInput(float input) {
        this.input = input;
    }

    public float getOutput() {
        return output;
    }

    public void setOutput(float output) {
        this.output = output;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode;
    }
}

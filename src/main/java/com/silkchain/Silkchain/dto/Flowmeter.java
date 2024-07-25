package com.silkchain.Silkchain.dto;

import javax.persistence.*;
import java.util.List;

@Entity(name = "flowmeter")
public class Flowmeter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    private List<FlowmeterInput> inputs;

    private float output;
    private String batchId;
    private String devicecode;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<FlowmeterInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<FlowmeterInput> inputs) {
        this.inputs = inputs;
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

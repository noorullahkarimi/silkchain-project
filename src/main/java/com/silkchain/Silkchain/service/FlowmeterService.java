package com.silkchain.Silkchain.service;

import com.silkchain.Silkchain.dto.Flowmeter;
import com.silkchain.Silkchain.dto.User;
import com.silkchain.Silkchain.repository.FlowmeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlowmeterService {
    @Autowired
    private FlowmeterRepository flowmeterRepository;

    public Flowmeter getRecordById(int id, String batchId) {
        Optional<Flowmeter> floOptional = flowmeterRepository.findByIdAndBatchId(id, batchId);

        if (!floOptional.isPresent()) {
            throw new ResourceNotFoundException("Flowmeter not found with id " + id + " and batchId " + batchId);
        }

        Flowmeter flo = floOptional.get();
        System.out.println("This is the device code _____> " + flo.getDevicecode());
        return flo;
    }
    public List<String> getInputsByWalletAddress(String walletAddress) {
        List<Object[]> results = flowmeterRepository.findInputsByWalletAddress(walletAddress);
        List<String> formattedResults = new ArrayList<>();

        for (Object[] result : results) {
            String formattedResult = result[0] + "," + result[1];
            formattedResults.add(formattedResult);
        }

        return formattedResults;
    }
}

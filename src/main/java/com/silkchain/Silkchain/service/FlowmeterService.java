package com.silkchain.Silkchain.service;

import com.silkchain.Silkchain.dto.Flowmeter;
import com.silkchain.Silkchain.dto.User;
import com.silkchain.Silkchain.repository.FlowmeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowmeterService {
    @Autowired
    private FlowmeterRepository flowmeterRepository;

    public Flowmeter saveItem(Flowmeter f) {
        return flowmeterRepository.save(f);
    }
}

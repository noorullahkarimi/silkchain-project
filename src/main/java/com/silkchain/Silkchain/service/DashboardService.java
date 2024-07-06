package com.silkchain.Silkchain.service;

import com.silkchain.Silkchain.dto.Flowmeter;
import com.silkchain.Silkchain.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    public List<Flowmeter> getAllRecords(){
        return dashboardRepository.findAll();
    }
}

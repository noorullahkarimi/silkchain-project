package com.silkchain.Silkchain.api;

import com.silkchain.Silkchain.dto.Flowmeter;
import com.silkchain.Silkchain.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardApi {

    @Autowired
    private DashboardService dashboardService;
    //show all record of flow-meter data for admin
    @PostMapping("/get-all-record")
    public List<Flowmeter> getAllRecordOfFlowMeter() {
        return dashboardService.getAllRecords();
    }

}

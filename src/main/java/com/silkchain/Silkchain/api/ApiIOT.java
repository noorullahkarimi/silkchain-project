package com.silkchain.Silkchain.api;
import com.silkchain.Silkchain.annotations.ApiKeyProtected;
import com.silkchain.Silkchain.dto.Flowmeter;
import com.silkchain.Silkchain.dto.FlowmeterInput;
import com.silkchain.Silkchain.repository.FlowmeterRepository;
import com.silkchain.Silkchain.service.FlowmeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/record")
public class ApiIOT {

    @Autowired
    private FlowmeterService flowmeterService;

    @Autowired
    private FlowmeterRepository flowmeterRepository;

    //this root takes the data from bio dessel set
    @PostMapping("/save")
    @ApiKeyProtected
    public ResponseEntity<String> saveFlowmeter(@RequestBody Flowmeter flowmeter) {
        System.out.print("it is called");
        flowmeterRepository.save(flowmeter);
        return new ResponseEntity<>("Flowmeter data saved successfully!", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
//    @ApiKeyProtected
    public ResponseEntity<String> updateFlowmeter(@PathVariable int id, @RequestBody Flowmeter updatedFlowmeter) {
        System.out.println("---------------------" + id + "----------------------");
        System.out.println(updatedFlowmeter.getBatchId());
        System.out.println(updatedFlowmeter.getDevicecode());
        System.out.println(updatedFlowmeter.getInputs());
        System.out.println("-------------------------------------------");
        Optional<Flowmeter> optionalFlowmeter = flowmeterRepository.findById(id);
        if (!optionalFlowmeter.isPresent()) {
            System.out.println("Flowmeter not found!");
            return new ResponseEntity<>("Flowmeter not found!", HttpStatus.NOT_FOUND);
        }

        Flowmeter existingFlowmeter = optionalFlowmeter.get();
        existingFlowmeter.setInputs(updatedFlowmeter.getInputs());
        existingFlowmeter.setOutput(updatedFlowmeter.getOutput());
        existingFlowmeter.setBatchId(updatedFlowmeter.getBatchId());
        existingFlowmeter.setDevicecode(updatedFlowmeter.getDevicecode());

        flowmeterRepository.save(existingFlowmeter);
        return new ResponseEntity<>("Flowmeter data updated successfully!", HttpStatus.OK);
    }
}
//----------------------------------save data-------------------------------
//        {
//        "inputs": [
//        {"input": 20},
//        {"input": 10},
//        {"input": 20}
//        ],
//        "output": 30.0,
//        "batchId": "some-uuid",
//        "devicecode": "device-123"
//        }
//------------------------------------update data-----------------------------------
//        {
//        "inputs": [
//        {"input": 20, "walletAddress": "0xbce17DD03a5a0628Dc73979EC4d392911069A2F7"},
//        {"input": 10, "walletAddress": "0xa3e17dd03a5a0628dc73979ec4d392911069a2f8"},
//        {"input": 20, "walletAddress": "0xc2e17dd03a5a0628dc73979ec4d392911069a2f9"},
//        // می‌توانید این لیست را با هر تعداد ورودی که نیاز دارید ادامه دهید
//        ],
//        "output": 30.0,
//        "batchId": "some-uuid",
//        "devicecode": "device-123"
//        }

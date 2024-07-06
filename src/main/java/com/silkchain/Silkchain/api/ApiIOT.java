package com.silkchain.Silkchain.api;
import com.silkchain.Silkchain.annotations.ApiKeyProtected;
import com.silkchain.Silkchain.dto.Flowmeter;
import com.silkchain.Silkchain.service.FlowmeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiIOT {

    @Autowired
    private FlowmeterService flowmeterService;

    //this root takes the data from bio dessel set
    @PostMapping("/save")
    @ApiKeyProtected
    public ResponseEntity<Void> index(@RequestBody Flowmeter flowmeter) {
        System.out.println("flow-meter is correct");
        System.out.println(flowmeter.getInput());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

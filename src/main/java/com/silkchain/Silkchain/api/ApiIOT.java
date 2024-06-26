package com.silkchain.Silkchain.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiIOT {
    //this root takes the data from bio dessel set
    @GetMapping("/save/{data}")
    public ResponseEntity<Void> index(@PathVariable String data) {
        System.out.println(data);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

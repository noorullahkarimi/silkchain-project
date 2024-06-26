package com.silkchain.Silkchain.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrudController {
    @GetMapping()
    public String index(){
        return "";
    }
}

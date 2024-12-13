package com.example.Travel_agency;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
    @GetMapping("/test/get")
    public int get() {
        return 1;
    }
}
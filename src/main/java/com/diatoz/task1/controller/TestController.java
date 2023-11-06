package com.diatoz.task1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/admin")
    public ResponseEntity<String> testAdmin() {
        return ResponseEntity.ok("admin is accessing");
    }

    @GetMapping("/user")
    public ResponseEntity<String> testUser() {
        return ResponseEntity.ok("user is accessing");
    }
}

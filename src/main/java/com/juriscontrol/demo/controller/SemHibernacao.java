package com.juriscontrol.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SemHibernacao {

    @GetMapping("/sem-hibernacao")
    public ResponseEntity<String> semHibernacao() {
        return ResponseEntity.ok("API está funcionando!");
    }
}

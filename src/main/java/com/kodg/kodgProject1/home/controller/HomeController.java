package com.kodg.kodgProject1.home.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> homeList() {

        return ResponseEntity.ok().body("확인");
    }
}

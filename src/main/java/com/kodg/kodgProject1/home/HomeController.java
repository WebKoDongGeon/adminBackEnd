package com.kodg.kodgProject1.home;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {


    @GetMapping("/home")
    public ResponseEntity<String> home() {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity.ok("확인");
    }
}

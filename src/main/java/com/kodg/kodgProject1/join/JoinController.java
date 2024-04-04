package com.kodg.kodgProject1.join;


import com.kodg.kodgProject1.join.JoinDto.SaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinController {

    private final JoinService joinService;


    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody SaveDto saveDto) {
        saveDto.setPermission("Basic");
        joinService.save(saveDto);


        return ResponseEntity.ok().build();
    }
}

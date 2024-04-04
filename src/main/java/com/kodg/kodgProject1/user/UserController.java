package com.kodg.kodgProject1.user;


import com.kodg.kodgProject1.user.userDto.FindUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public ResponseEntity<FindUserDto> findUser(@RequestBody FindUserDto findUserDto) {

//        FindUserDto user = userService.findUser(findUserDto);

        return ResponseEntity.ok().build();

    }
}

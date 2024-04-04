package com.kodg.kodgProject1.join;


import com.kodg.kodgProject1.join.JoinDto.SaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final JoinMapper joinMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void save(SaveDto saveDto) {

        /**
         * 동일한 회원이 있는지 검증하는부분 추가.
         * */

        System.out.println("2");
        SaveDto data = new SaveDto();

        data.setUserName(saveDto.getUserName());
        data.setPermission(saveDto.getPermission());
        data.setUserId(saveDto.getUserId());
        data.setUserPw(bCryptPasswordEncoder.encode(saveDto.getUserPw()));
        joinMapper.save(data);

    };

}

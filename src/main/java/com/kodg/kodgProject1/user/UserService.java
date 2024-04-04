package com.kodg.kodgProject1.user;


import com.kodg.kodgProject1.user.userDto.FindUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        System.out.println("여기? : "+ userName);
        //DB에서 조회
        FindUserDto userData = userMapper.findUser(userName);

        System.out.println("userData : "+userData);


        if(userName != null) {

            //UserDetails에 담아서 return하면 AuthenticationManager가 검증 함
            return new CustomUserDetails(userData);
        }

        return null;
    }

//    public FindUserDto findUser(FindUserDto dto) {
//        FindUserDto user = userMapper.findUser(dto.getUserName());
//
//        return user;
//    }


}

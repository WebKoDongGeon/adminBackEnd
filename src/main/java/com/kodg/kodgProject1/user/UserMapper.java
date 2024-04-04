package com.kodg.kodgProject1.user;

import com.kodg.kodgProject1.user.userDto.FindUserDto;
import com.kodg.kodgProject1.user.userDto.RefreshDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface UserMapper {

    FindUserDto findUser(String userName);

    @Transactional
    void deleteRefresh(String refresh);

    Boolean findRefresh(String refresh);

    void saveToken(RefreshDto refreshDto);

}

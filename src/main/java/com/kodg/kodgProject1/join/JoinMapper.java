package com.kodg.kodgProject1.join;


import com.kodg.kodgProject1.join.JoinDto.SaveDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {

    void save(SaveDto saveDto);

}

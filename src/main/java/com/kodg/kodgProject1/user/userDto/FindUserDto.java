package com.kodg.kodgProject1.user.userDto;


import lombok.Data;

@Data
public class FindUserDto {


    private Long user_no;
    private String user_name;
    private String user_id;
    private String user_pw;
    private String permission;

}

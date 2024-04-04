package com.kodg.kodgProject1.user;


import lombok.RequiredArgsConstructor;
import com.kodg.kodgProject1.user.userDto.FindUserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final FindUserDto findUserDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return findUserDto.getPermission();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return findUserDto.getUser_pw();
    }

    @Override
    public String getUsername() {
        return findUserDto.getUser_name();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

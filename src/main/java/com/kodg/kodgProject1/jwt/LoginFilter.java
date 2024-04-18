package com.kodg.kodgProject1.jwt;


import com.kodg.kodgProject1.user.CustomUserDetails;
import com.kodg.kodgProject1.user.UserMapper;
import com.kodg.kodgProject1.user.userDto.RefreshDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserMapper userMapper;

    //Authentication 객체 만들어서 리턴 => 의존 : AuthenticationManager
    //인증 요청시에 실행되는 함수 => /login

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        System.out.println("JWT 진입");

        //클라이언트 요청에서 username, password 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        //스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);


        //token에 담은 검증을 위한 AuthenticationManager로 전달.
        return authenticationManager.authenticate(authToken);
    }



    /**로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨*/
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        String username = authentication.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String permission = auth.getAuthority();

        //토큰 생성
        String access = jwtUtil.createJwt("access", username, permission, 600000L);
        String refresh = jwtUtil.createJwt("refresh", username, permission, 86400000L);

        //Refresh 토큰 저장
        addRefreshEntity(username, refresh, 86400000L);


        //응답 설정
        response.setHeader("access", access);
        response.addCookie(createCookie("refresh", refresh));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());

        PrintWriter writer = response.getWriter();
        writer.println(access+"&");
        writer.println(username+"&");
        writer.println(permission);

    }

    /**로그인 실패시 실행하는 메소드*/
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setStatus(401);
    }

    private void addRefreshEntity(String userName, String refresh, Long expiredMs) {
        System.out.println("리프레시 : " +refresh);
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshDto refreshDto = new RefreshDto();

        refreshDto.setUserName(userName);
        refreshDto.setRefresh(refresh);
        refreshDto.setExpiration(date.toString());

        userMapper.saveToken(refreshDto);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);

        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}

package com.example.demo.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;
//계정의 권한에 따라 다음 페이지를 정해주는곳
@Component
public class CustomAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)  {
        try {
            // 사용자의 권한을 확인하여 리다이렉트할 URL 결정
            for (GrantedAuthority auth : authentication.getAuthorities()) {
                if (auth.getAuthority().equals("ROLE_ADMIN")) {
                    redirectStrategy.sendRedirect(request, response, "/admin");
                    return;
                } else if (auth.getAuthority().equals("ROLE_MEMBER")) {
                    redirectStrategy.sendRedirect(request, response, "/");
                    return;
                }
            }
            // 기본적으로 모든 사용자는 "/"로 리다이렉트
            redirectStrategy.sendRedirect(request, response, "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


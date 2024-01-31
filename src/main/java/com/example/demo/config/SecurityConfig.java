package com.example.demo.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration      //설정 할 때 필수 @Controller, @Service 와 비슷한 맥락
@EnableWebSecurity //스프링 시큐리티 활성화,웹 보안설정을 구성할때 사용됨 이게 있어야 필터체인이 자동생성되고 웹 보안을 활성화함
@Log4j2
public class SecurityConfig {


    @Bean
    //암호화 모듈
    public PasswordEncoder passwordEncoder() {
        try {           //null값 오류를 예외처리 위해
            log.info("passwordEncoder 진입");
            return new BCryptPasswordEncoder();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("PasswordEncoder로 부터 에러발생", e);
        }


    }


    @Bean
    //접근 URL, 접근시 필요한 권한 설정 하는곳
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        log.info("------FilterChain start------");
        http
                .csrf((csrf) -> csrf.disable())   //csrf 크로스 뭐시기 공격을 방지하는 보안을 끄는 작업 내부에서만 돌릴거면 상관 x
                //authorizeRequests : Url에 따른 페이지에 대한 권한을 부여하기 위해 시작하는 메소드
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                                .requestMatchers("/login/**","/css/**","/img/**").permitAll() //괄호 안의 URL 접근은 권한없어도 ok
                                .requestMatchers("/admin/**").hasRole("ADMIN")//어드민은 회원 관리만 가능합니다.
                                .anyRequest().hasRole("MEMBER")  //그 외의 접근은 작업관련이기에 "MEMBER"만 접근 가능
//                        .anyRequest().hasAnyRole("ADMIN", "MEMBER")  //그 외의 접근은 "ADMIN","MEMBER"가 있어야 합니다. (없으면 로그인창 )
                )
                //기본제공 로그인 폼 설정
                .formLogin((formLogin) -> formLogin
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .loginPage("/login")
                )
                //기본제공 로그아웃 폼  로그아웃은 미구현!
                .logout(withDefaults())
                //세션 설정
                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionConcurrency((sessionConcurrency) -> sessionConcurrency
                                .maximumSessions(1)
                                .expiredUrl("/login?expired")
                        ));
        return http.build();
    }
}



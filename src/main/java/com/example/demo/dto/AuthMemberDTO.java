package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


//별도의 클래스를 만들어서 이를 DTO 처럼 사용하는 방식  p.512
//p.513
//1.User 클래스를 상속받는다
//2.부모요소의 생성자를 호출 (username ,password , Collection<?extends GrantedAuthority> authorities)
/*
    3. 아래는 예시

public class AuthMemberDTO extends User {

    public AuthMemberDTO(String username, String password, Collection<? extends GrantedAuthority>authorities){
        super(username,password,authorities);
    }
}
*/

//교과서 응용 p.514
@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User {
    //SpirngSecurity의 User 클래스를 불러옴
    //User는 username , password 등등 정해진

    private String id;
    private String password;
    //AuthMemberDTO는 DTO역할을 수행 + 스프링시큐리티에 인가/인증 작업에 사용가능
        public AuthMemberDTO(
                String username,
                String password,
                Collection<? extends GrantedAuthority> authorities) {

            super(username,password,authorities);
            this.id = username;
            this.password=password;
        }
}


package com.example.demo.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

//암호화는 문자열로 알아보기 어렵기 때문에
//테스트 코드로 미리 어떤값들을 사용 할 수 있는지 확인해 두는것이 좋습니다.
@SpringBootTest
public class PasswordTests {
    @Autowired
    private PasswordEncoder passwordEncoder; //SecurityConfig 로부터 불러옴

    @Test   //Test폴더의 필수사항 있어야 정상테스트 가능

    public void testEncode() {
        String password = "admin";
        String enPw = passwordEncoder.encode(password); //password를 변환
        System.out.println("enPw:" + enPw); //변환된 1111을 보여주겠지
        boolean matchResult =  passwordEncoder.matches(password,enPw); //password 와 enPw(변환된 password) 가 같나?
        System.out.println("matchResult:" + matchResult); //바로 위 결과를 출력
    }
}

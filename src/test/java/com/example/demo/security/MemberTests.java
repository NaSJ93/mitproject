package com.example.demo.security;


import com.example.demo.mainEntity.Member;
import com.example.demo.mainEntity.MemberRole;
import com.example.demo.mainRepogitory.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

//테스트 코드 작성해서 Id와 암호화된 비밀번호를 DB에 3개정도 넣어보자 p.506 하단
@SpringBootTest //필수
public class MemberTests {
    @Autowired //MemberRepository 연결
    private MemberRepository repository;
    @Autowired //암호화 모듈 연결
    private PasswordEncoder passwordEncoder;

    @Test   //필수 2
    public void insertDummies() { //더미데이터 넣는 코드 작성
        Member member = Member.builder()  //Member.builder()의 항목별 내용 작성하기 (빌더로)
                .id("0")
                .password(passwordEncoder.encode("0"))
                .build();
        member.addMemberRole(MemberRole.ADMIN);
        repository.save(member); //위 내용을 member 테이블에 저장


    }

//    @Test 반복문 활용 
//    public void findAllMembers(){ //메서드 이름 및 반환 타입 수정
//        List<Member> members = repository.findAll();
//        for (Member member : members) {
//
//        System.out.println(member.getId());
//
//        }
//    }


    //.stream() 을 활용한 전체 id 조회
    @Test
    public void findAllMembers() {
        List<Member> members = repository.findAll();
        System.out.println("멤버 목록 출력 시작멤버 목록 출력 시작멤버 목록 출력 시작");
        members.stream()
                .map(Member::getId)
                .forEach(System.out::println);
        System.out.println("멤버 목록 출력 끝 멤버 목록 출력 끝 멤버 목록 출력 끝");
    }
}

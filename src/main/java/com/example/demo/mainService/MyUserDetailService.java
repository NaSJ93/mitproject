package com.example.demo.mainService;

import  com.example.demo.mainEntity.Member;
import com.example.demo.mainRepogitory.MemberRepository;
import com.example.demo.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
p.515
사용자의 정보를 가져오는 핵심적인 역할 담당
스프링 시큐리티의 인증을 담당하는 AuthenticationManager(인증 관리자)
인증 관리자는 내부적으로 UserDetailService를 찾고 호출함
여기서 UserDetailService가 이용하는 구조로 작성할 거임 그래야 인식하고 가져다 쓰니까
 */

@Service
@Log4j2
@RequiredArgsConstructor //추가 MemberRepository와 연동과정 1
public class MyUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository; // memberRepository 외부로부터변경 불가선언 연동과정 2

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MyUserDetailService loadUserByUsername" + username); //예외처리시 로그출력


        Optional<Member> optionalMember = memberRepository.findById(username);
        Member member = optionalMember.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. " + username));

        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getId(),
                member.getPassword(),
                member.getRoleSet()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toSet())
        );
        log.info("MyUserDetailService loadUserByUsername: " + username);



        log.info("Found user: " + member.getId());



        return authMemberDTO;



    }
}

package com.example.demo.mainService;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mainEntity.Member;
import com.example.demo.mainRepogitory.MemberRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//생성자를 자동으로 생성해줌
@RequiredArgsConstructor
@Builder
public class MemberServiceImpl implements MemberService{
//비즈니스 로직 구현
//콘트롤러로부터 받은 요청에 대해 필요한 데이터를 조작하는 작업을 수행
// DB와 상호작용, 외부시스템 통신,복잡한 계산업무 처리

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;





    public void registerUser(MemberDTO memberDTO) {
        // 입력받은 비밀번호 암호화하여 저장
        String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
        // MemberDTO에서 필요한 필드를 추출 -> 엔티티로 변환
        Member member = Member.builder()
                .id(memberDTO.getId())
                .password(encodedPassword)
                .roleSet(memberDTO.getRoleSet())
                .build();
//        member.addMemberRole(MemberRole.MEMBER);
        memberRepository.save(member);  // DB member 테이블에 저장
    }
    //MemberDTO의 내용을 가진 List 타입의 getAllMembers 클래스 생성
    public List<MemberDTO> getAllMembers(){
        //member 리포짓토리에서 모두 읽어옴,
        List<Member> members = memberRepository.findAll();
//
        return  members.stream().map(MemberDTO::fromEntity).collect(Collectors.toList());
    }

    //멤버 삭제


    @Override
    public void deleteMember(String id) {
        memberRepository.deleteById(id);

    }
}

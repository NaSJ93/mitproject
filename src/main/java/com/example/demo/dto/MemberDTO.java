package com.example.demo.dto;

import com.example.demo.mainEntity.Member;
import com.example.demo.mainEntity.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//입력,저장 순서
//View (타임리프 등) -> Controller -> Service -> Repository/DAO -> Entity
//DB로부터 출력시
//Entity -> Repository/DAO -> Service -> Controller -> View (타임리프 등)



public class MemberDTO {

    private String id;
    private String password;
//    Member 엔티티에서 e Set<MemberRole> roleSet = new HashSet<>(); 으로 설정했음
    private Set<MemberRole> roleSet;

    //        Member entity 에서 MemberDTO로 빌더를 이용해서 변환 static 필수?
    public static MemberDTO fromEntity(Member member){

        return MemberDTO.builder()
                .id(member.getId())
                .password(member.getPassword())
                .roleSet(member.getRoleSet())
                .build();
    }

}

package com.example.demo.mainEntity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity {
    @Id
    private String id;

    private String password;


    //Member 가 MemberRole 타입값을 처리하기 위해서 Set<MemberRole>타입을 추가하고 Fetch는 Lazy타입으로 지정 p.505
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole memberRole){ //test코드에서 활용 할거임 권한부여 메소드
        roleSet.add(memberRole);
    }


}




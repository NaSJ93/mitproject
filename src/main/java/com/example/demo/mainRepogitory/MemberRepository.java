package com.example.demo.mainRepogitory;

//Member의 데이터중 패스워드는 암호화해서 데이터를 추가해야 하기때문에 (날 것 말고)
// MemberRepository 이름의 인터페이스 추가 테스트 클래스 실행해서 DB에 기입되도록   p.506
//test폴더에서 계속...

import com.example.demo.mainEntity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member,String> {  //<Entity클래스명,Entity클래스의 ID 타입>


}

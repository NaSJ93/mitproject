package com.example.demo.MainController;

import com.example.demo.dto.MemberDTO;
import com.example.demo.mainService.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    private final MemberService memberService;

    @Autowired
    public AdminController(MemberService memberService){
        this.memberService=memberService;
    }
 
//회원가입 부분

    //회원을 생성하는 메서드
    @GetMapping("/admin")
    public String adminPageGet(Model model){
        System.out.println("회원 추가 폼 진입Get");
        // Thymeleaf에서 사용할 빈 MemberDTO 객체를 생성하고 모델에 추가
        model.addAttribute("member",new MemberDTO());


        //멤버 목록 출력
        List<MemberDTO> members = memberService.getAllMembers();
        model.addAttribute("members", members);


        return "admin";



    }
    @PostMapping("/admin/delete")
    public String deleteMember(@RequestParam("id") String id){
        System.out.println("계정 삭제 시작합니다.");

        memberService.deleteMember(id);

        System.out.println("계정 삭제 끝.");
    return "redirect:/admin";
    }


    @PostMapping("/admin/register")

    public String createMemberForm(@ModelAttribute MemberDTO memberDTO){
    //MemberService를 통해 회원을 생성
        memberService.registerUser(memberDTO);
    System.out.println("회원 생성 진행중");
    //생성이 완료되면 /admin 페이지로 리다이렉트

    System.out.println("생성완료 회원가입 폼으로 돌아갑니다.");

        return "redirect:/admin";
    }


}

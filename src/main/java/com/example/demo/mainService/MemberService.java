package com.example.demo.mainService;

import com.example.demo.dto.MemberDTO;

import java.util.List;

public interface MemberService {

    void deleteMember(String id);

    List<MemberDTO> getAllMembers();

    void registerUser(MemberDTO memberDTO);
}

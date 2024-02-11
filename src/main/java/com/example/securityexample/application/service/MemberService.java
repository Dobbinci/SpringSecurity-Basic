package com.example.securityexample.application.service;

import com.example.securityexample.application.dto.MemberDto;
import com.example.securityexample.domain.Role;
import com.example.securityexample.domain.entity.Member;
import com.example.securityexample.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberRepository memberRepository;

    public Long addMember(MemberDto dto) {

        String rawPassword = dto.getPassword();

        Member member = Member.toMember(dto);

        member.setPassword(bCryptPasswordEncoder.encode(rawPassword));
        member.setRole(Role.USER.name()); // 일반 사용자로 권한 설정
        // member.setRole(Role.ADMIN.name()); // 관리자로 권한 설정

        Member addedMember = memberRepository.save(member);

        return addedMember.getMemberId();

    }
}

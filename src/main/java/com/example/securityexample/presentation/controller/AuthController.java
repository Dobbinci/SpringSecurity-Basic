package com.example.securityexample.presentation.controller;

import com.example.securityexample.application.dto.MemberDto;
import com.example.securityexample.application.service.MemberService;
import com.example.securityexample.config.auth.PrincipalDetails;
import com.example.securityexample.presentation.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest request) {

        Long memberId = memberService.addMember(MemberDto.from(request));
        URI uri = URI.create("/sign-up/" + memberId);

        return ResponseEntity.created(uri).build();
    }

    // 세션을 통해 사용자 정보를 접근하는 예제
    @GetMapping("/info")
    public ResponseEntity<Long> getInfo(@AuthenticationPrincipal PrincipalDetails member) {

        Long userId = member.getMemberId();

        return ResponseEntity.ok(userId);
    }


}
